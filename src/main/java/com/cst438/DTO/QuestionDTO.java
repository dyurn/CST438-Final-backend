package com.cst438.DTO;

public record QuestionDTO(
    String question_text,
    String option_a,
    String option_b,
    String option_c,
    String option_d,
    String correct_answer
) {}
