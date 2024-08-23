package com.surveys_hell.response_options.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveys_hell.response_options.domain.entity.ResponseOptions;

public interface ResponseOptionsService {
    void createResponseOptions (ResponseOptions responseOptions);
    Optional<ResponseOptions> findResponseOptions (int id);
    void updateResponseOptions (ResponseOptions responseOptions);
    void deleteResponseOptions (int id);
    List<ResponseOptions> findResponseByQuestion(int questionId);
}
