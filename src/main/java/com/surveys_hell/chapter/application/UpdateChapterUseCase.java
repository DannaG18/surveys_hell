package com.surveys_hell.chapter.application;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.chapter.domain.service.ChapterService;

public class UpdateChapterUseCase {
    private final ChapterService chapterService;

    public UpdateChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void execute(Chapter chapter) {
        chapterService.updateChapter(chapter);
    }
}
