package com.surveys_pro.dto;

public class SubresponseOptionsDto {
    private int id;
    private String name;
    private String options;

    public SubresponseOptionsDto(){}

    public SubresponseOptionsDto(int id, String name, String options) {
        this.id = id;
        this.name = name;
        this.options = options;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    
}
