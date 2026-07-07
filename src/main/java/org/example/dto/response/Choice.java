package org.example.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.dto.request.Message;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Choice {
    private Message message;
    private String finish_reason;
}
