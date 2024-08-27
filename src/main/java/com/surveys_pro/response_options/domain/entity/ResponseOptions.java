package com.surveys_pro.response_options.domain.entity;

import java.sql.Date;

public class ResponseOptions {
    private int id;
    private int optionValue;
    private int categoryCatalogId;
    private Date createdAt;
    private int parentResponse;
    private int questionId;
    private Date updatedAt;
    private String typeComponentHtml;
    private String commentReponse;
    private String optionText;
    
    public ResponseOptions() {
    }

    public ResponseOptions(int id, int optionValue, int categoryCatalogId, Date createdAt, int parentResponse,
            int questionId, Date updatedAt, String typeComponentHtml, String commentReponse, String optionText) {
        this.id = id;
        this.optionValue = optionValue;
        this.categoryCatalogId = categoryCatalogId;
        this.createdAt = createdAt;
        this.parentResponse = parentResponse;
        this.questionId = questionId;
        this.updatedAt = updatedAt;
        this.typeComponentHtml = typeComponentHtml;
        this.commentReponse = commentReponse;
        this.optionText = optionText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(int optionValue) {
        this.optionValue = optionValue;
    }

    public int getCategoryCatalogId() {
        return categoryCatalogId;
    }

    public void setCategoryCatalogId(int categoryCatalogId) {
        this.categoryCatalogId = categoryCatalogId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getParentResponse() {
        return parentResponse;
    }

    public void setParentResponse(int parentResponse) {
        this.parentResponse = parentResponse;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTypeComponentHtml() {
        return typeComponentHtml;
    }

    public void setTypeComponentHtml(String typeComponentHtml) {
        this.typeComponentHtml = typeComponentHtml;
    }

    public String getCommentReponse() {
        return commentReponse;
    }

    public void setCommentReponse(String commentReponse) {
        this.commentReponse = commentReponse;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
