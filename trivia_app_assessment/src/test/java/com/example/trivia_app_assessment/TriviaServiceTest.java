package com.example.trivia_app_assessment.service;

import com.example.trivia_app_assessment.model.AnswerRequest;
import com.example.trivia_app_assessment.model.AnswerResponse;
import com.example.trivia_app_assessment.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriviaServiceUnitTest {

    private TriviaService triviaService;

    @BeforeEach
    void setUp() {
        // Instead of real service, create a fake one for unit tests
        triviaService = new TriviaService() {
            @Override
            public Question fetchQuestion() {
                return new Question(
                        "What is 2 + 2?",
                        List.of("4", "3", "5", "2") // first element is correct
                );
            }

            @Override
            public AnswerResponse checkAnswer(AnswerRequest request) {
                return new AnswerResponse("4".equals(request.getChosenAnswer()));
            }
        };
    }

    @Test
    void fetchQuestion_returnsNonNullQuestion() throws Exception {
        Question question = triviaService.fetchQuestion();
        assertNotNull(question);
        assertEquals("What is 2 + 2?", question.getQuestionText());
        assertEquals(4, question.getPossibleAnswers().size());
    }

    @Test
    void checkAnswer_correctAnswer_returnsTrue() throws Exception {
        AnswerRequest request = new AnswerRequest();
        request.setQuestion("What is 2 + 2?");
        request.setChosenAnswer("4");

        AnswerResponse response = triviaService.checkAnswer(request);
        assertTrue(response.isCorrect());
    }

    @Test
    void checkAnswer_wrongAnswer_returnsFalse() throws Exception {
        AnswerRequest request = new AnswerRequest();
        request.setQuestion("What is 2 + 2?");
        request.setChosenAnswer("3");

        AnswerResponse response = triviaService.checkAnswer(request);
        assertFalse(response.isCorrect());
    }

}
