package com.example.trivia_app_assessment.model;

import java.util.List;

/**
 * Represents a trivia question shown to the user.
 * This class is a simple data holder (SRP).
 */
public class Question {
    private String questionText;
    private List<String> possibleAnswers;

    public Question(String questionText, List<String> possibleAnswers) {
        this.questionText = questionText;
        this.possibleAnswers = possibleAnswers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }
}

