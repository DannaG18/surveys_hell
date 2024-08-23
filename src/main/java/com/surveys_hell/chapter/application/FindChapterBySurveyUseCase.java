package com.surveys_hell.chapter.application;

import com.surveys_hell.chapter.domain.service.ChapterService;
import java.util.List;
import com.surveys_hell.chapter.domain.entity.Chapter;

public class FindChapterBySurveyUseCase {
    private final ChapterService chapterService;

    public FindChapterBySurveyUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public List<Chapter> execute(int id) {
        return chapterService.findChapterBySurvey(id);
    }
}
