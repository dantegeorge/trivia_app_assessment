package com.example.trivia_app_assessment.model;

/**
 * Represents the result after checking the user's answer.
 */
public class AnswerResponse {
    private boolean correct;

    public AnswerResponse(boolean correct) {
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }
}

