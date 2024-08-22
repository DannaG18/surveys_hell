package com.surveys_hell.response_question.application;

import com.surveys_hell.response_question.domain.service.ResponseQuestionService;

public class DeleteResponseQuestionUseCase {
    private final ResponseQuestionService responseQuestionService;

    public DeleteResponseQuestionUseCase(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public void execute(int id){
        responseQuestionService.deleteResponseQuestion(id);
    }
}
