package com.surveys_pro.response_options.application;

import com.surveys_pro.response_options.domain.entity.ResponseOptions;
import com.surveys_pro.response_options.domain.service.ResponseOptionsService;

public class CreateResponseOptionsUseCase {
    private final ResponseOptionsService responseOptionsService;

    public CreateResponseOptionsUseCase(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public void execute(ResponseOptions responseOptions) {
        responseOptionsService.createResponseOptions(responseOptions);
    }
}
