package com.cst438.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.DTO.UserResponseDTO;
import com.cst438.domain.Question;
import com.cst438.domain.QuestionRepository;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/check")
    public ResponseEntity<String> checkAnswer(@RequestBody UserResponseDTO userResponse) {
        Optional<Question> questionOpt = questionRepository.findById(userResponse.getQuestionId());

        if (questionOpt.isPresent()) {
            Question question = questionOpt.get();
            boolean isCorrect = question.getCorrect_answer().equals(userResponse.getSelectedAnswer());
            return ResponseEntity.ok(isCorrect ? "Correct" : "Incorrect");
        }

        return ResponseEntity.badRequest().body("Question not found");
    }
}
