package org.example.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class LlmRequest {
    private String model;
    private List<Message> messages;
}
