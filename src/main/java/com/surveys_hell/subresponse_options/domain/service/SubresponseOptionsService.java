package com.surveys_hell.subresponse_options.domain.service;

import java.util.Optional;
import java.util.List;

import com.surveys_hell.subresponse_options.domain.entity.SubresponseOptions;

public interface SubresponseOptionsService {
    void createSubresponseOptions (SubresponseOptions subresponseOptions);
    Optional<SubresponseOptions> findSubresponseOptions (int id);
    void updateSubresponseOptions (SubresponseOptions subresponseOptions);
    void deleteSubresponseOptions (int id);
    List<SubresponseOptions> findSubResponseByResponse(int responseId);
}
