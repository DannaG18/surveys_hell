package com.surveys_hell.ui.surveydirector.application;

import java.util.List;

import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;

public class FindResponseUseCase {
    private final SurveyDirectorService surveyDirectorService;

    public FindResponseUseCase(SurveyDirectorService surveyDirectorService) {
        this.surveyDirectorService = surveyDirectorService;
    }

    public List<ResponseOptions> execute(int id) {
        return surveyDirectorService.findResponseByQuestion(id);
    }
}
