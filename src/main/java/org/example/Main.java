package org.example;

import org.example.dto.LlmModel;
import org.example.dto.request.Message;
import org.example.service.LlmClient;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        LlmClient llm = new LlmClient("https://api.mistral.ai",
                System.getenv("API_KEY"),
                LlmModel.MISTRAL_LARGE_3.getModelName()
        );

        String answer = llm.chat(List.of(
                        new Message("system", "your are president"),
                        new Message("user", "hello")
                )
        );

        System.out.println(answer);
    }
}