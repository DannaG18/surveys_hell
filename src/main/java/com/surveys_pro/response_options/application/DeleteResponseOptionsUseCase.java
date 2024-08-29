package com.surveys_pro.response_options.application;

import com.surveys_pro.response_options.domain.service.ResponseOptionsService;

public class DeleteResponseOptionsUseCase {
    private final ResponseOptionsService responseOptionsService;

    public DeleteResponseOptionsUseCase(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public void execute(int id) {
        responseOptionsService.deleteResponseOptions(id);
    }
}
