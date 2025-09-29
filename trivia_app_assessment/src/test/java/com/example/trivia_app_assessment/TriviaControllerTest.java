package com.example.trivia_app_assessment.controller;

import com.example.trivia_app_assessment.model.AnswerRequest;
import com.example.trivia_app_assessment.model.AnswerResponse;
import com.example.trivia_app_assessment.model.Question;
import com.example.trivia_app_assessment.service.TriviaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TriviaControllerTest {

    private TriviaService triviaService;
    private TriviaController triviaController;

    @BeforeEach
    void setUp() {
        triviaService = mock(TriviaService.class);
        triviaController = new TriviaController(triviaService);
    }

    @Test
    void getQuestion_returnsQuestionFromService() throws Exception {
        Question mockQuestion = new Question(
                "Sample?",
                List.of("A","B","C","D")
        );

        when(triviaService.fetchQuestion()).thenReturn(mockQuestion);

        Question result = triviaController.getQuestion();
        assertEquals("Sample?", result.getQuestionText());
        assertEquals(4, result.getPossibleAnswers().size());
    }

    @Test
    void checkAnswer_callsServiceAndReturnsResponse() {
        AnswerRequest request = new AnswerRequest();
        request.setQuestion("Sample?");
        request.setChosenAnswer("A");

        AnswerResponse mockResponse = new AnswerResponse(true);

        when(triviaService.checkAnswer(request)).thenReturn(mockResponse);

        AnswerResponse result = triviaController.checkAnswer(request);
        assertTrue(result.isCorrect());

        verify(triviaService, times(1)).checkAnswer(request);
    }
}
