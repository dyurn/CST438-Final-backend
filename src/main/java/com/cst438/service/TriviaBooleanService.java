package com.cst438.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst438.DTO.UserBooleanResponseDTO;
import com.cst438.domain.BooleanQuestion;
import com.cst438.domain.TriviaApiResponse;

@Service
public class TriviaBooleanService {

    private final RestTemplate restTemplate;
    
    private final String URL = "https://opentdb.com/api.php?amount=50&type=boolean";
    
    private Map<String, String> questionCache = new HashMap<>();
    
    private List<BooleanQuestion> cachedQuestions = new ArrayList<>();
    
    private List<BooleanQuestion> questions = new ArrayList<>();

    public TriviaBooleanService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<BooleanQuestion> fetchTriviaBooleanQuestions() {
        TriviaApiResponse response = restTemplate.getForObject(URL, TriviaApiResponse.class);
        return response != null ? response.getResults() : new ArrayList<>();
    }
    
    public BooleanQuestion fetchRandomTriviaBooleanQuestion() {
        TriviaApiResponse response = restTemplate.getForObject(URL, TriviaApiResponse.class);
        
        if (response != null && !response.getResults().isEmpty()) {
            Collections.shuffle(response.getResults());
            return response.getResults().get(0);
        }

        return null;
    }
    
//    public void fetchAndCacheTriviaQuestions() {
//        String url = "https://opentdb.com/api.php?amount=50&type=boolean";
//        TriviaApiResponse response = restTemplate.getForObject(URL, TriviaApiResponse.class);
//        
//        if (response != null && response.getResults() != null) {
//            for (BooleanQuestion question : response.getResults()) {
//                // Stocker la question et la réponse correcte dans le cache.
//                questionCache.put(question.getQuestion(), question.getCorrect_answer());
//            }
//        }
//    }
    
    public void fetchAndCacheTriviaQuestions() {
        TriviaApiResponse response = restTemplate.getForObject(URL, TriviaApiResponse.class);
        
        if (response != null && response.getResults() != null) {
            cachedQuestions.addAll(response.getResults());
        }
    }
    
    public BooleanQuestion getNextQuestion() {
        if (questions.isEmpty()) {
            // Chargez les questions ici ou gérez l'absence de questions
            fetchTriviaBooleanQuestions(); 
        }
        if (!questions.isEmpty()) {
            int randomIndex = new Random().nextInt(questions.size());
            return questions.remove(randomIndex);
        } else {
            // Gérer le cas où il n'y a pas de questions disponibles
            return null;
        }
    }


    public boolean checkTriviaBooleanAnswer(UserBooleanResponseDTO userResponse) {
        String correctAnswer = questionCache.get(userResponse.getQuestion());
        return correctAnswer != null && correctAnswer.equalsIgnoreCase(userResponse.getSelectedAnswer());
    }
}

