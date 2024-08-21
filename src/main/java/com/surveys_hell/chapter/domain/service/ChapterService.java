package com.surveys_hell.chapter.domain.service;

import java.util.Optional;

import com.surveys_hell.chapter.domain.entity.Chapter;

public interface ChapterService {
    void createChapter (Chapter chapter);
    Optional<Chapter> findChapter (int id);
    void updateChapter (Chapter chapter);
    void deleteChapter (int id);
}
