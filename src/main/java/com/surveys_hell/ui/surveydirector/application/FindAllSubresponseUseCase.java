package com.surveys_hell.ui.surveydirector.application;

import java.util.List;

import com.surveys_hell.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;

public class FindAllSubresponseUseCase {
    private final SurveyDirectorService surveyDirectorService;

    public FindAllSubresponseUseCase(SurveyDirectorService surveyDirectorService) {
        this.surveyDirectorService = surveyDirectorService;
    }

    public List<SubresponseOptions> execute(int id) {
        return surveyDirectorService.findSubresponseByQuestion(id);
    }
}
