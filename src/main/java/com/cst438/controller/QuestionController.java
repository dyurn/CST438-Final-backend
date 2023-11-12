package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cst438.domain.Question;
import com.cst438.DTO.QuestionDTO;
import com.cst438.domain.QuestionRepository;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionRepository.findAll());
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDTO questionDTO) {
        Question newQuestion = new Question(
            questionDTO.questionText(),
            questionDTO.optionA(),
            questionDTO.optionB(),
            questionDTO.optionC(),
            questionDTO.optionD(),
            questionDTO.correctAnswer()
        );

        Question savedQuestion = questionRepository.save(newQuestion);
        return ResponseEntity.ok(savedQuestion);
    }

    @PutMapping("/{questionId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> updateQuestion(@PathVariable("questionId") Long questionId, @RequestBody QuestionDTO questionDTO) {
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (!questionOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Question question = questionOpt.get();
        question.setQuestionText(questionDTO.questionText());
        question.setOptionA(questionDTO.optionA());
        question.setOptionB(questionDTO.optionB());
        question.setOptionC(questionDTO.optionC());
        question.setOptionD(questionDTO.optionD());
        question.setCorrectAnswer(questionDTO.correctAnswer());

        questionRepository.save(question);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/{questionId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") Long questionId) {
        if (!questionRepository.existsById(questionId)) {
            return ResponseEntity.notFound().build();
        }

        questionRepository.deleteById(questionId);
        return ResponseEntity.noContent().build();
    }
}
