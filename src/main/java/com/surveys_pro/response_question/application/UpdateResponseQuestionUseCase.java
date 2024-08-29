package com.surveys_pro.response_question.application;

import com.surveys_pro.response_question.domain.entity.ResponseQuestion;
import com.surveys_pro.response_question.domain.service.ResponseQuestionService;

public class UpdateResponseQuestionUseCase {
    private final ResponseQuestionService responseQuestionService;

    public UpdateResponseQuestionUseCase(ResponseQuestionService responseQuestionService) {
        this.responseQuestionService = responseQuestionService;
    }

    public void execute(ResponseQuestion responseQuestion){
        responseQuestionService.updateResponseQuestion(responseQuestion);
    }
}
