package com.surveys_hell.survey.application;

import com.surveys_hell.survey.domain.entity.Survey;
import com.surveys_hell.survey.domain.service.SurveyService;

public class UpdateSurveyUseCase {
    private final SurveyService surveyService;

    public UpdateSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute(Survey survey) {
        surveyService.updateSurvey(survey);
    }
}
