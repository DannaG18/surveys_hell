package com.surveys_pro.survey.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveys_pro.dto.SurveyDto;
import com.surveys_pro.survey.domain.entity.Survey;

public interface SurveyService {
    void createSurvey (Survey survey);
    Optional<Survey> findSurvey (int id);
    Optional<Survey> findSurveyByName(String name);
    void updateSurvey (Survey survey);
    void deleteSurvey (int id);
    void getAllSurvey(List<Survey> names);
    Optional<SurveyDto> findSurveyDto (int id);
}
