package com.surveys_hell.survey.application;

import com.surveys_hell.survey.domain.service.SurveyService;
import javax.swing.JComboBox;
import java.util.List;

public class GetAllSurveyUseCase {
    private final SurveyService surveyService;

    public GetAllSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute(List<String> names) {
        surveyService.getAllSurvey(names);
    }
}
