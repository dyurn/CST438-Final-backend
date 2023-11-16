package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cst438.domain.Question;
import com.cst438.DTO.QuestionDTO;
import com.cst438.domain.QuestionRepository;
import com.cst438.service.QuestionService;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionRepository.findAll());
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDTO questionDTO) {
        Question newQuestion = new Question(
            questionDTO.question_text(),
            questionDTO.option_a(),
            questionDTO.option_b(),
            questionDTO.option_c(),
            questionDTO.option_d(),
            questionDTO.correct_answer()
        );

        Question savedQuestion = questionRepository.save(newQuestion);
        return ResponseEntity.ok(savedQuestion);
    }

    @PutMapping("/{question_id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> updateQuestion(@PathVariable("question_id") int question_id, @RequestBody QuestionDTO questionDTO) {
        Optional<Question> questionOpt = questionRepository.findById(question_id);
        if (!questionOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Question question = questionOpt.get();
        question.setQuestion_text(questionDTO.question_text());
        question.setOption_a(questionDTO.option_a());
        question.setOption_b(questionDTO.option_b());
        question.setOption_c(questionDTO.option_c());
        question.setOption_d(questionDTO.option_d());
        question.setCorrect_answer(questionDTO.correct_answer());

        questionRepository.save(question);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/{questionId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") int questionId) {
        if (!questionRepository.existsById(questionId)) {
            return ResponseEntity.notFound().build();
        }

        questionRepository.deleteById(questionId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/random")
    public ResponseEntity<Question> getRandomQuestion() {
        Optional<Question> question = questionService.getRandomQuestion();
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
