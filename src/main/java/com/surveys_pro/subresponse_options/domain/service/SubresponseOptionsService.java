package com.surveys_pro.subresponse_options.domain.service;

import java.util.Optional;

import com.surveys_pro.dto.SubresponseOptionsDto;
import com.surveys_pro.subresponse_options.domain.entity.SubresponseOptions;

import java.util.List;

public interface SubresponseOptionsService {
    void createSubresponseOptions (SubresponseOptions subresponseOptions);
    Optional<SubresponseOptions> findSubresponseOptions (int id);
    void updateSubresponseOptions (SubresponseOptions subresponseOptions);
    void deleteSubresponseOptions (int id);
    List<SubresponseOptionsDto> findSubresponseOptionsDto(int id);
}
