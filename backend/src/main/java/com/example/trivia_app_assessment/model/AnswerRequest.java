package com.example.trivia_app_assessment.model;

/**
 * Represents the user's submitted answer.
 */
public class AnswerRequest {
    private String question;
    private String chosenAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(String chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
}

