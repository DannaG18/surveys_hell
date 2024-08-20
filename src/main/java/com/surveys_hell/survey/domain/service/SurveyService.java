package com.surveys_hell.survey.domain.service;

import java.util.Optional;
import java.util.List;

import com.surveys_hell.survey.domain.entity.Survey;

public interface SurveyService {
    void createSurvey (Survey survey);
    Optional<Survey> findSurvey (int id);
    void updateSurvey (Survey survey);
    void deleteSurvey (int id);
    void getAllSurvey(List<String> names);
}
