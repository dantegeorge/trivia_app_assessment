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

    // Just keep the latest correct answer in a list
    private final List<String> correctAnswers = new ArrayList<>();

    public Question fetchQuestion() throws Exception {
        String url = "https://opentdb.com/api.php?amount=1";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode results = root.path("results");
        if (results.isEmpty()) return null;

        JsonNode result = results.get(0);
        String type = result.get("type").asText();

        // Decode everything so comparisons work later
        String questionText = result.get("question").asText();
        String correctAnswer = result.get("correct_answer").asText();

        List<String> allAnswers = new ArrayList<>();
        if ("multiple".equals(type)) {
            result.get("incorrect_answers").forEach(ans ->
                    allAnswers.add(ans.asText())
            );
            allAnswers.add(correctAnswer);
            Collections.shuffle(allAnswers);
        } else if ("boolean".equals(type)) {
            allAnswers.add("True");
            allAnswers.add("False");
        }

        // Reset and store only the latest correct answer
        correctAnswers.clear();
        correctAnswers.add(correctAnswer);
        System.out.println("Correct answer is: " + correctAnswer);
        return new Question(questionText, allAnswers);
    }

    public AnswerResponse checkAnswer(AnswerRequest request) {
        if (correctAnswers.isEmpty()) {
            return new AnswerResponse(false);
        }
        // Compare against the single stored correct answer
        String storedAnswer = correctAnswers.get(0);
        boolean isCorrect = storedAnswer.equals(request.getChosenAnswer());
        System.out.println("Correct answer is: " + storedAnswer + isCorrect);
        return new AnswerResponse(isCorrect);
    }
}
