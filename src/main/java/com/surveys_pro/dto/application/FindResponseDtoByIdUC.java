package com.surveys_pro.dto.application;

import java.util.List;

import com.surveys_pro.dto.ResponseOptionsDto;
import com.surveys_pro.response_options.domain.service.ResponseOptionsService;

public class FindResponseDtoByIdUC {
    private final ResponseOptionsService responseOptionsService;

    public FindResponseDtoByIdUC(ResponseOptionsService responseOptionsService) {
        this.responseOptionsService = responseOptionsService;
    }



    public List<ResponseOptionsDto> execute(int id){
        return responseOptionsService.findResponseDto(id);
    }
}
