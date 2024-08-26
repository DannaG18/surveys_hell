package com.surveys_hell.survey_user.application;

import com.surveys_hell.survey_user.domain.entity.SurveyUser;
import com.surveys_hell.survey_user.domain.service.SurveyUserService;

public class CreateSurveyUserUseCase {
    private final SurveyUserService surveyUserService;

    public CreateSurveyUserUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public void execute(SurveyUser surveyUser) {
        surveyUserService.createSurveyUser(surveyUser);
    }
}
