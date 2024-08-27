package com.surveys_pro.subresponse_options.application;

import java.util.Optional;

import com.surveys_pro.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_pro.subresponse_options.domain.service.SubresponseOptionsService;

public class FindSubresponseOptionsUseCase {
    private final SubresponseOptionsService subresponseOptionsService;

    public FindSubresponseOptionsUseCase(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public Optional<SubresponseOptions> execute(int id) {
        return subresponseOptionsService.findSubresponseOptions(id);
    }
}
