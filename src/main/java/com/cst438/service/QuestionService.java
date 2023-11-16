package com.cst438.service;

import com.cst438.domain.Question;
import com.cst438.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getRandomQuestion() {
        return questionRepository.findRandomQuestion();
        
    }
}
