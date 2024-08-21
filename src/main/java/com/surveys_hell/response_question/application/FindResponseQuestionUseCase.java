package com.surveys_hell.response_question.application;

import java.util.Optional;

import com.surveys_hell.response_question.domain.entity.ResponseQuestion;
import com.surveys_hell.response_question.domain.service.ResponseQuestionService;

public class FindResponseQuestionUseCase {
    private final ResponseQuestionService responseQuestionService;

    public FindResponseQuestionUseCase(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public Optional<ResponseQuestion> execute(int id){
        return responseQuestionService.findResponseQuestion(id);
    }
}
