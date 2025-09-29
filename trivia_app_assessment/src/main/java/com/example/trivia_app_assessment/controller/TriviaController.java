package com.example.trivia_app_assessment.controller;

import com.example.trivia_app_assessment.model.AnswerRequest;
import com.example.trivia_app_assessment.model.AnswerResponse;
import com.example.trivia_app_assessment.model.Question;
import com.example.trivia_app_assessment.service.TriviaService;
import org.springframework.web.bind.annotation.*;
// For returning internal Server Errors
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
/**
 * REST Controller exposing endpoints to the frontend.
 */
@RestController
@RequestMapping("/api")
public class TriviaController {

    private final TriviaService triviaService;

    // Constructor Injection
    public TriviaController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }

    @GetMapping("/questions")
    public Question getQuestion() {
        try {
            return triviaService.fetchQuestion();
        } catch (Exception e) {
            // Return 500 Internal Server Error if something goes wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch question", e);
        }
    }

    @PostMapping("/check-answers")
    public AnswerResponse checkAnswer(@RequestBody AnswerRequest request) {
        return triviaService.checkAnswer(request);
    }
}





