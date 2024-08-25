package com.surveys_hell.ui.surveydirector.application;

import com.surveys_hell.ui.surveydirector.domain.entity.SurveyDirector;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;

public class CreateSurveyDIrectorUseCase {
    private final SurveyDirectorService surveyDirectorService;

    public CreateSurveyDIrectorUseCase(SurveyDirectorService surveyDirectorService) {
        this.surveyDirectorService = surveyDirectorService;
    }

    public void execute(SurveyDirector surveyDirector) {
        surveyDirectorService.createSurveyDirector(surveyDirector);
    }
}
