package com.surveys_pro.survey.application;

import java.util.Optional;

import com.surveys_pro.survey.domain.entity.Survey;
import com.surveys_pro.survey.domain.service.SurveyService;

public class FindSurveyByNameUseCase {
    private final SurveyService surveyService;

    public FindSurveyByNameUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Optional<Survey> execute(String name) {
        return surveyService.findSurveyByName(name);
    }
}
