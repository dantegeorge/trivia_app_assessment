package com.example.trivia_app_assessment.service;

import com.example.trivia_app_assessment.model.AnswerRequest;
import com.example.trivia_app_assessment.model.AnswerResponse;
import com.example.trivia_app_assessment.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class handling trivia logic.
 */
@Service
public class TriviaService {
    // In-memory store for correct answers
    private final List<String> correctAnswers = new ArrayList<>();

    public Question fetchQuestion() throws Exception {
        String url = "https://opentdb.com/api.php?amount=1&type=multiple";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode results = root.path("results");
        if (results.isEmpty()) return null;

        JsonNode result = results.get(0);
        String questionText = result.get("question").asText();
        String correctAnswer = result.get("correct_answer").asText();

        List<String> allAnswers = new ArrayList<>();
        result.get("incorrect_answers").forEach(ans -> allAnswers.add(ans.asText()));
        allAnswers.add(correctAnswer);
        // So a user can't tell based on order
        Collections.shuffle(allAnswers);

        correctAnswers.clear();
        correctAnswers.add(correctAnswer);

        return new Question(questionText, allAnswers);
    }

    public AnswerResponse checkAnswer(AnswerRequest request) {
        boolean isCorrect = correctAnswers.contains(request.getChosenAnswer());
        return new AnswerResponse(isCorrect);
    }
}
