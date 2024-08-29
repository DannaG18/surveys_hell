package com.surveys_pro.response_question.domain.service;

import java.util.Optional;

import com.surveys_pro.response_question.domain.entity.ResponseQuestion;

public interface ResponseQuestionService {
    void createResponseQuestion (ResponseQuestion responseQuestion);
    Optional<ResponseQuestion> findResponseQuestion (int id);
    void updateResponseQuestion (ResponseQuestion responseQuestion);
    void deleteResponseQuestion (int id);
}
