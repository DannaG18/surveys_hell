package com.surveys_hell.ui.surveydirector.application;

import java.util.List;

import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;

public class FindQuestionUseCase {
    private final SurveyDirectorService surveyDirectorService;

    public FindQuestionUseCase(SurveyDirectorService surveyDirectorService) {
        this.surveyDirectorService = surveyDirectorService;
    }

    public List<Question> execute(int idChapter) {
        return surveyDirectorService.findQuestionByCategory(idChapter);
    }
}
