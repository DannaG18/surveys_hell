package com.surveys_pro.survey.application;

import java.util.Optional;

import com.surveys_pro.survey.domain.entity.Survey;
import com.surveys_pro.survey.domain.service.SurveyService;

public class FindSurveyUseCase {
    private final SurveyService surveyService;

    public FindSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Optional<Survey> execute(int id) {
        return surveyService.findSurvey(id);
    }
}
