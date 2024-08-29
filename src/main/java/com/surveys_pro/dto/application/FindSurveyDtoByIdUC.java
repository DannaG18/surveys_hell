package com.surveys_pro.dto.application;

import java.util.Optional;

import com.surveys_pro.dto.SurveyDto;
import com.surveys_pro.survey.domain.service.SurveyService;

public class FindSurveyDtoByIdUC {
    private final SurveyService surveyService;

    public FindSurveyDtoByIdUC(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Optional<SurveyDto> execute(int id) {
        return surveyService.findSurveyDto(id);
    }
}
