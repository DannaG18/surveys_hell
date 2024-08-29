package com.surveys_pro.dto.application;

import com.surveys_pro.chapter.domain.service.ChapterService;
import com.surveys_pro.dto.ChapterDto;

import java.util.List;

public class FindChapterDtoByIdUC {
    private final ChapterService  chapterService;


    public FindChapterDtoByIdUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public List<ChapterDto> execute(int id){
        return chapterService.findChapterDto(id);
    }
}
