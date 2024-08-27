package com.surveys_pro.chapter.application;

import java.util.List;

import com.surveys_pro.chapter.domain.entity.Chapter;
import com.surveys_pro.chapter.domain.service.ChapterService;

public class FindChapterBySurveyUseCase {
    private final ChapterService chapterService;

    public FindChapterBySurveyUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public List<Chapter> execute(int id) {
        return chapterService.findChapterBySurvey(id);
    }
}
