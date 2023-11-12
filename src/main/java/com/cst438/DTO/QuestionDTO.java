package com.cst438.DTO;

public record QuestionDTO(
    String questionText,
    String optionA,
    String optionB,
    String optionC,
    String optionD,
    char correctAnswer
) {}
