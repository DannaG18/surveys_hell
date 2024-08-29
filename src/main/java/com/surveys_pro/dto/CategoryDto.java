package com.surveys_pro.dto;

import java.util.List;

public class CategoryDto {
    private int id;
    private String name;
    private List<ResponseOptionsDto> childResponseOptions;

    public CategoryDto() {
        
    }

    public CategoryDto(int id, String name, List<ResponseOptionsDto> childResponseOptions) {
        this.id = id;
        this.name = name;
        this.childResponseOptions = childResponseOptions;
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

    public List<ResponseOptionsDto> getChildResponseOptions() {
        return childResponseOptions;
    }

    public void setChildResponseOptions(List<ResponseOptionsDto> childResponseOptions) {
        this.childResponseOptions = childResponseOptions;
    }

    

    
}
