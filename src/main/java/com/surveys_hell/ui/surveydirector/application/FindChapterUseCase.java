package com.surveys_hell.ui.surveydirector.application;

import java.util.List;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.ui.surveydirector.domain.service.SurveyDirectorService;

public class FindChapterUseCase {
    private final SurveyDirectorService surveyDirectorService;

    public FindChapterUseCase(SurveyDirectorService surveyDirectorService) {
        this.surveyDirectorService = surveyDirectorService;
    }

    public List<Chapter> execute(int id) {
        return surveyDirectorService.findChapterBySurvey(id);
    }
}
