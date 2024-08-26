package com.surveys_hell.survey_user.application;

import com.surveys_hell.survey_user.domain.entity.SurveyUser;
import com.surveys_hell.survey_user.domain.service.SurveyUserService;

public class UpdateSurveyUserUseCase {
    private final SurveyUserService surveyUserService;

    public UpdateSurveyUserUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public void execute(SurveyUser surveyUser) {
        surveyUserService.updateSurveyUser(surveyUser);
    }
}
