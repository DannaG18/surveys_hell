package com.surveys_hell.ui.surveydirector.domain.entity;

public class SurveyDirector {
    private int id;
    private int response_id;
    private Integer subresponse_id;
    private String responseText;
    public SurveyDirector() {
    }
    public SurveyDirector(int id, int response_id, int subresponse_id, String responseText) {
        this.id = id;
        this.response_id = response_id;
        this.subresponse_id = subresponse_id;
        this.responseText = responseText;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getResponse_id() {
        return response_id;
    }
    public void setResponse_id(int response_id) {
        this.response_id = response_id;
    }
    public Integer getSubresponse_id() {
        return subresponse_id;
    }
    public void setSubresponse_id(Integer subresponse_id) {
        this.subresponse_id = subresponse_id;
    }
    public String getResponseText() {
        return responseText;
    }
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    
}
