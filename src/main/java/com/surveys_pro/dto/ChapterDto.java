package com.surveys_pro.dto;

import java.util.List;

public class ChapterDto {
    private int id;
    private String name;
    private String chapterNumber;
    private List<QuestionDto> childQuestion;

    
    public ChapterDto() {
    }


    public String getChapterNumber() {
        return chapterNumber;
    }


    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }


    public ChapterDto(int id, String name, String chapterNumber, List<QuestionDto> childQuestion) {
        this.id = id;
        this.name = name;
        this.chapterNumber = chapterNumber;
        this.childQuestion = childQuestion;
    }


    public ChapterDto(int id, String name, List<QuestionDto> childQuestion) {
        this.id = id;
        this.name = name;
        this.childQuestion = childQuestion;
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


    public List<QuestionDto> getChildQuestion() {
        return childQuestion;
    }


    public void setChildQuestion(List<QuestionDto> childQuestion) {
        this.childQuestion = childQuestion;
    }

    
}
