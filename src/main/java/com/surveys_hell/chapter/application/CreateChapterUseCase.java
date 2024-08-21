package com.surveys_hell.chapter.application;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.chapter.domain.service.ChapterService;

public class CreateChapterUseCase {
    private final ChapterService chapterService;

    public CreateChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void execute(Chapter chapter) {
        chapterService.createChapter(chapter);
    }
}
