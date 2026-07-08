package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LlmModel {
    MISTRAL_LARGE_3("mistral-large-latest", 0.5, 1.5),
    MISTRAL_MEDIUM_3_5("mistral-medium-latest", 1.5, 7.5),
    MISTRAL_SMALL_4("mistral-small-latest", 0.15, 0.6),
    MINISTRAL_3_8_B("ministral-8b-latest", 0.15, 0.15);

    private String modelName;
    private double priceInput;
    private double priceOutput;
}
