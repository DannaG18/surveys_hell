package com.surveys_hell.survey.application;

import java.util.List;

import com.surveys_hell.survey.domain.entity.Survey;
import com.surveys_hell.survey.domain.service.SurveyService;

public class GetAllSurveyUseCase {
    private final SurveyService surveyService;

    public GetAllSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute(List<Survey> surveys) {
        surveyService.getAllSurvey(surveys);
    }
}
