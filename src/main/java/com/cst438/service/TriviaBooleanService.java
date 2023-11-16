package com.cst438.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.BooleanQuestion;
import com.cst438.domain.TriviaApiResponse;

@Service
public class TriviaBooleanService {

    private final RestTemplate restTemplate;

    public TriviaBooleanService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<BooleanQuestion> fetchTriviaBooleanQuestions() {
        String url = "https://opentdb.com/api.php?amount=50&type=boolean";
        TriviaApiResponse response = restTemplate.getForObject(url, TriviaApiResponse.class);
        return response != null ? response.getResults() : new ArrayList<>();
    }
    
    public BooleanQuestion fetchRandomTriviaBooleanQuestion() {
        String url = "https://opentdb.com/api.php?amount=50&type=boolean";
        TriviaApiResponse response = restTemplate.getForObject(url, TriviaApiResponse.class);
        
        if (response != null && !response.getResults().isEmpty()) {
            Collections.shuffle(response.getResults());
            return response.getResults().get(0);
        }

        return null;
    }
}

