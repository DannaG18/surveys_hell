package com.surveys_pro.chapter.domain.entity;

import java.sql.Date;

public class Chapter {
    private int id;
    private Date createdAt;
    private Date updatedAt;
    private int surveyId;
    private String chapterNumber;
    private String chapterTitle;
    public Chapter() {
    }
    public Chapter(int id, Date created_at, Date updated_at, int surveyId, String chapterNumber, String chapterTitle) {
        this.id = id;
        this.createdAt = created_at;
        this.updatedAt = updated_at;
        this.surveyId = surveyId;
        this.chapterNumber = chapterNumber;
        this.chapterTitle = chapterTitle;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public int getSurveyId() {
        return surveyId;
    }
    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
    public String getChapterNumber() {
        return chapterNumber;
    }
    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }
    public String getChapterTitle() {
        return chapterTitle;
    }
    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    
}
