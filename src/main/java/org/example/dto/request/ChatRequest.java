package org.example.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class ChatRequest {
    private final String model;
    private final List<Message> messages;
}
