package com.surveys_pro.dto;

import java.util.List;

public class ResponseOptionsDto {
    private int id;
    private String name;
    private int categoryId;
    private String categoryName;
    private List<SubresponseOptionsDto> childSubresponse;

    
    public ResponseOptionsDto() {
    }


    public ResponseOptionsDto(int id, String name, int categoryId, String categoryName,
            List<SubresponseOptionsDto> childSubresponse) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.childSubresponse = childSubresponse;
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


    public int getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public String getCategoryName() {
        return categoryName;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public List<SubresponseOptionsDto> getChildSubresponse() {
        return childSubresponse;
    }


    public void setChildSubresponse(List<SubresponseOptionsDto> childSubresponse) {
        this.childSubresponse = childSubresponse;
    }




    
    
}
