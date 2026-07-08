package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dto.LlmModel;
import org.example.dto.request.ChatRequest;
import org.example.dto.request.Message;
import org.example.dto.response.ChatResponse;
import org.example.dto.response.ChatResult;
import org.example.dto.response.Usage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
public class LlmClient {
    private final String baseUrl;
    private final String apiKey;
    private final LlmModel model;

    private HttpClient httpClient = HttpClient.newHttpClient();
    private ObjectMapper json = new ObjectMapper();

    public ChatResult chat(List<Message> messages) throws IOException, InterruptedException {
        String body = json.writeValueAsString(new ChatRequest(model.getModelName(), messages));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("LLM API error: " + response.statusCode() + " : " + response.body());
        }

        ChatResponse chatParsedResponse = json.readValue(response.body(), ChatResponse.class);
        Usage usage = chatParsedResponse.getUsage();
        String content = chatParsedResponse.getChoices().get(0).getMessage().getContent();
        BigDecimal cost = calculateCost(usage);

        System.out.println("[usage tokens] input=" + usage.getPrompt_tokens() + " output=" + usage.getCompletion_tokens() + " cost= " + cost + " $");

        return new ChatResult(content, usage);
    }

    private BigDecimal calculateCost(Usage usage) {
        BigDecimal inputCost = BigDecimal.valueOf(usage.getPrompt_tokens())
                .multiply(BigDecimal.valueOf(model.getPriceInput()));

        BigDecimal outputCost = BigDecimal.valueOf(usage.getCompletion_tokens())
                .multiply(BigDecimal.valueOf(model.getPriceOutput()));

        return inputCost.add(outputCost)
                .divide(BigDecimal.valueOf(1_000_000));
    }
}
