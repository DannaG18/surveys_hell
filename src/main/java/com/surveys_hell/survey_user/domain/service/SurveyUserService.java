package com.surveys_hell.survey_user.domain.service;

import java.util.Optional;

import com.surveys_hell.survey_user.domain.entity.SurveyUser;


public interface SurveyUserService {
    void createSurveyUser (SurveyUser surveyUser);
    Optional<SurveyUser> findSurveyUser (int survey_id, int user_id);
    void updateSurveyUser (SurveyUser surveyUser);
    void deleteSurveyUser (int survey_id, int user_id);
}
