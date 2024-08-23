package com.surveys_hell.survey.application;

import java.util.Optional;

import com.surveys_hell.survey.domain.entity.Survey;
import com.surveys_hell.survey.domain.service.SurveyService;

public class FindSurveyByNameUseCase {
    private final SurveyService surveyService;

    public FindSurveyByNameUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Optional<Survey> execute(String name) {
        return surveyService.findSurveyByName(name);
    }
}
