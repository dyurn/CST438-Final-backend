package com.cst438.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.DTO.UserBooleanResponseDTO;
import com.cst438.DTO.UserResponseDTO;
import com.cst438.domain.Question;
import com.cst438.domain.QuestionRepository;
import com.cst438.service.TriviaBooleanService;

@CrossOrigin
@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private TriviaBooleanService triviaBooleanService;

    @Autowired
    private QuestionRepository questionRepository;

    // Endpoint pour vérifier les réponses aux questions MCQ
    @PostMapping("/check/mcq")
    public ResponseEntity<String> checkMCQAnswer(@RequestBody UserResponseDTO userResponse) {
        Optional<Question> questionOpt = questionRepository.findById(userResponse.getQuestionId());

        if (questionOpt.isPresent()) {
            Question question = questionOpt.get();
            boolean isCorrect = question.getCorrect_answer().equals(userResponse.getSelectedAnswer());
            return ResponseEntity.ok(isCorrect ? "Correct" : "Incorrect");
        }

        return ResponseEntity.badRequest().body("Question not found");
    }

    // Endpoint pour vérifier les réponses aux questions vrai/faux
    @PostMapping("/check/boolean")
    public ResponseEntity<String> checkBooleanAnswer(@RequestBody UserBooleanResponseDTO userResponse) {
        triviaBooleanService.fetchAndCacheTriviaQuestions();
        boolean isCorrect = triviaBooleanService.checkTriviaBooleanAnswer(userResponse);

        return ResponseEntity.ok(isCorrect ? "Correct" : "Incorrect");
    }
}

