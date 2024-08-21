package com.surveys_hell.subresponse_options.domain.service;

import java.util.Optional;

import com.surveys_hell.subresponse_options.domain.entity.SubresponseOptions;

public interface SubresponseOptionsService {
    void createSubresponseOptions (SubresponseOptions subresponseOptions);
    Optional<SubresponseOptions> findSubresponseOptions (int id);
    void updateSubresponseOptions (SubresponseOptions subresponseOptions);
    void deleteSubresponseOptions (int id);
}
