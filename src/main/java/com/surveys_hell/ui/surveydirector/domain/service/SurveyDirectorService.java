package com.surveys_hell.ui.surveydirector.domain.service;

import java.util.List;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.question.domain.entity.Question;
import com.surveys_hell.response_options.domain.entity.ResponseOptions;
import com.surveys_hell.subresponse_options.domain.entity.SubresponseOptions;
import com.surveys_hell.ui.surveydirector.domain.entity.SurveyDirector;

public interface SurveyDirectorService {
    public List<Chapter> findChapterBySurvey(int id);
    public List<Question> findQuestionByCategory(int idChapter);
    public List<ResponseOptions> findResponseByQuestion(int id);
    public List<SubresponseOptions> findSubresponseByQuestion(int id);
    public void createSurveyDirector(SurveyDirector surveyDirector);
}
