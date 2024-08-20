package com.surveys_hell.survey.application;

import com.surveys_hell.survey.domain.service.SurveyService;

public class DeleteSurveyUseCase {
    private final SurveyService surveyService;

    public DeleteSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute (int id) {
        surveyService.deleteSurvey(id);
    }
}
