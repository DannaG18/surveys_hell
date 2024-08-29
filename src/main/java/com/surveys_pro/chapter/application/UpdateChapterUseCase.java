package com.surveys_pro.chapter.application;

import com.surveys_pro.chapter.domain.entity.Chapter;
import com.surveys_pro.chapter.domain.service.ChapterService;

public class UpdateChapterUseCase {
    private final ChapterService chapterService;

    public UpdateChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void execute(Chapter chapter) {
        chapterService.updateChapter(chapter);
    }
}
