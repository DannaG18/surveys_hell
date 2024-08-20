package com.surveys_hell.survey.application;

import java.util.Optional;

import com.surveys_hell.survey.domain.entity.Survey;
import com.surveys_hell.survey.domain.service.SurveyService;

public class FindSurveyUseCase {
    private final SurveyService surveyService;

    public FindSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Optional<Survey> execute(int id) {
        return surveyService.findSurvey(id);
    }
}
