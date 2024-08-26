package com.surveys_hell.survey_user.application;

import java.util.Optional;

import com.surveys_hell.survey_user.domain.entity.SurveyUser;
import com.surveys_hell.survey_user.domain.service.SurveyUserService;

public class FindSurveyUserUseCase {
    private final SurveyUserService surveyUserService;

    public FindSurveyUserUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public Optional<SurveyUser> execute(int survey_id, int user_id) {
        return surveyUserService.findSurveyUser(survey_id, user_id);
    }
}
