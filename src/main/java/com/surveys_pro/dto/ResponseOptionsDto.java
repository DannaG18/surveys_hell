package com.surveys_pro.dto;

import java.util.List;

public class ResponseOptionsDto {
    private int id;
    private String name;
    private List<CategoryDto> childCategory;
    private List<SubresponseOptionsDto> childSubresponse;

    public ResponseOptionsDto(){}

    public ResponseOptionsDto(int id, String name, List<CategoryDto> childCategory,
            List<SubresponseOptionsDto> childSubresponse) {
        this.id = id;
        this.name = name;
        this.childCategory = childCategory;
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

    public List<CategoryDto> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<CategoryDto> childCategory) {
        this.childCategory = childCategory;
    }

    public List<SubresponseOptionsDto> getChildSubresponse() {
        return childSubresponse;
    }

    public void setChildSubresponse(List<SubresponseOptionsDto> childSubresponse) {
        this.childSubresponse = childSubresponse;
    }
    
}
