package com.surveys_hell.survey_user.application;

import com.surveys_hell.survey_user.domain.service.SurveyUserService;

public class DeleteSurveyUserUseCase {
    private final SurveyUserService surveyUserService;

    public DeleteSurveyUserUseCase(SurveyUserService surveyUserService) {
        this.surveyUserService = surveyUserService;
    }

    public void execute(int survey_id, int user_id) {
        surveyUserService.deleteSurveyUser(survey_id, user_id);
    }
}
