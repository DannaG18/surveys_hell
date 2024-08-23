package com.surveys_hell.response_options.application;

import java.util.List;

import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.response_options.domain.service.ResponseOptionsService;

public class FindResponseOptionByQuestionUseCase {
    private final ResponseOptionsService responseOptionsService;

    public FindResponseOptionByQuestionUseCase(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public List<ResponseOptions> execute (int id) {
        return responseOptionsService.findResponseByQuestion(id);
    }
}
