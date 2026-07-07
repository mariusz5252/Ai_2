package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LlmModel {
    MISTRAL_LARGE_3("mistral-large-latest"),
    MISTRAL_MEDIUM_3_5("mistral-medium-latest"),
    MISTRAL_SMALL_4("mistral-small-latest"),
    MINISTRAL_3_8_B("ministral-8b-latest");

    private String modelName;
}
