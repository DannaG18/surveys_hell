package com.surveys_pro.chapter.domain.service;

import java.util.Optional;

import com.surveys_pro.chapter.domain.entity.Chapter;
import com.surveys_pro.dto.ChapterDto;

import java.util.List;

public interface ChapterService {
    void createChapter (Chapter chapter);
    Optional<Chapter> findChapter (int id);
    void updateChapter (Chapter chapter);
    void deleteChapter (int id);
    List<Chapter> findChapterBySurvey(int surveyId);
    List<ChapterDto> findChapterDto (int id);
}
