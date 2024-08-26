package com.surveys_hell.survey_user.domain.entity;

public class SurveyUser {
    private int user_id;
    private int survey_id;
    public SurveyUser() {
    }
    public SurveyUser(int survey_id, int user_id) {
        this.user_id = user_id;
        this.survey_id = survey_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getSurvey_id() {
        return survey_id;
    }
    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    
}
