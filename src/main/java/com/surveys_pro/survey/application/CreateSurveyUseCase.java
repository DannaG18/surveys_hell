package com.surveys_pro.survey.application;

import com.surveys_pro.survey.domain.entity.Survey;
import com.surveys_pro.survey.domain.service.SurveyService;

public class CreateSurveyUseCase {
    private final SurveyService surveyService;

    public CreateSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute(Survey survey) {
        surveyService.createSurvey(survey);
    }
}
