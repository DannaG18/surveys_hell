package com.surveys_pro.subresponse_options.application;

import com.surveys_pro.subresponse_options.domain.service.SubresponseOptionsService;

public class DeleteSubresponseOptionsUseCase {
    private final SubresponseOptionsService subresponseOptionsService;

    public DeleteSubresponseOptionsUseCase(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public void execute(int id) {
        subresponseOptionsService.deleteSubresponseOptions(id);
    }
}   
