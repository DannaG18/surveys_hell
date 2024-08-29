package com.surveys_pro.dto;

import java.util.List;

public class SurveyDto {
    private int id;
    private String name;
    private List<ChapterDto> childChapter;

    
    public SurveyDto() {
    }


    public SurveyDto(int id, String name, List<ChapterDto> childChapter) {
        this.id = id;
        this.name = name;
        this.childChapter = childChapter;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<ChapterDto> getChildChapter() {
        return childChapter;
    }


    public void setChildChapter(List<ChapterDto> childChapter) {
        this.childChapter = childChapter;
    }

    
}
