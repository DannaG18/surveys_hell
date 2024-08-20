package com.surveys_hell.chapter.application;

import java.util.Optional;

import com.surveys_hell.chapter.domain.entity.Chapter;
import com.surveys_hell.chapter.domain.service.ChapterService;

public class FindChapterUseCase {
    private final ChapterService chapterService;

    public FindChapterUseCase(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public Optional<Chapter> execute(int id) {
        return chapterService.findChapter(id);
    }
}
