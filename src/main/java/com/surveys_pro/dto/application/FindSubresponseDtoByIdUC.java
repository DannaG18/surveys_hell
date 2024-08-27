package com.surveys_pro.dto.application;

import com.surveys_pro.dto.SubresponseOptionsDto;
import com.surveys_pro.subresponse_options.domain.service.SubresponseOptionsService;

import java.util.List;

public class FindSubresponseDtoByIdUC {
    private final SubresponseOptionsService subresponseOptionsService;

    public FindSubresponseDtoByIdUC(SubresponseOptionsService subresponseOptionsService) {
        this.subresponseOptionsService = subresponseOptionsService;
    }

    public List<SubresponseOptionsDto> execute(int id){
        return subresponseOptionsService.findSubresponseOptionsDto(id);
    }
}
