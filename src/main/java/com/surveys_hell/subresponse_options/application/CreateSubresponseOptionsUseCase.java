package com.surveys_hell.subresponse_options.application;

import com.surveys_hell.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_hell.subresponse_options.domain.service.SubresponseOptionsService;

public class CreateSubresponseOptionsUseCase {
    private final SubresponseOptionsService subresponseOptionsService;

    public CreateSubresponseOptionsUseCase(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public void execute(SubresponseOptions subresponseOptions) {
        subresponseOptionsService.createSubresponseOptions(subresponseOptions);
    }
}
