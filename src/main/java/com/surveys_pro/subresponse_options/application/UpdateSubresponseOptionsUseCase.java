package com.surveys_pro.subresponse_options.application;

import com.surveys_pro.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_pro.subresponse_options.domain.service.SubresponseOptionsService;

public class UpdateSubresponseOptionsUseCase {
    private final SubresponseOptionsService subresponseOptionsService;

    public UpdateSubresponseOptionsUseCase(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public void execute(SubresponseOptions subresponseOptions) {
        subresponseOptionsService.updateSubresponseOptions(subresponseOptions);
    }
}
