package com.surveys_hell.response_options.application;

import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.response_options.domain.service.ResponseOptionsService;

public class UpdateResponseOptionsUseCase {
    private final ResponseOptionsService responseOptionsService;

    public UpdateResponseOptionsUseCase(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public void execute(ResponseOptions responseOptions) {
        responseOptionsService.updateResponseOptions(responseOptions);
    }
}
