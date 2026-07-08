package org.example.dto.response;

import lombok.Data;

@Data
public class ChatResult {
    private final String content;
    private final Usage usage;
}
