package com.surveys_pro.question.domain.entity;

import java.sql.Date;

public class Question {
    private int id;
    private int chapterId;
    private Date createdAt;
    private Date updatedAt;
    private String questionNumber;
    private String responseType;
    private String commentQuestion;
    private String questionText;
    public Question() {
    }
    public Question(int id, int chapterId, Date createdAt, Date updatedAt, String questionNumber, String responseType,
            String commentQuestion, String questionText) {
        this.id = id;
        this.chapterId = chapterId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.questionNumber = questionNumber;
        this.responseType = responseType;
        this.commentQuestion = commentQuestion;
        this.questionText = questionText;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getChapterId() {
        return chapterId;
    }
    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
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
    public String getQuestionNumber() {
        return questionNumber;
    }
    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }
    public String getResponseType() {
        return responseType;
    }
    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
    public String getCommentQuestion() {
        return commentQuestion;
    }
    public void setCommentQuestion(String commentQuestion) {
        this.commentQuestion = commentQuestion;
    }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    
}
