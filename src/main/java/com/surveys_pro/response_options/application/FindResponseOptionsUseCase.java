package com.surveys_pro.response_options.application;

import java.util.Optional;

import com.surveys_pro.response_options.domain.entity.ResponseOptions;
import com.surveys_pro.response_options.domain.service.ResponseOptionsService;

public class FindResponseOptionsUseCase {
    private final ResponseOptionsService responseOptionsService;

    public FindResponseOptionsUseCase(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }

    public Optional<ResponseOptions> execute(int id) {
        return responseOptionsService.findResponseOptions(id);
    }
}
