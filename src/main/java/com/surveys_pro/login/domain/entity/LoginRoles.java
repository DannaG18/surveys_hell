package com.surveys_pro.login.domain.entity;

public class LoginRoles {
    private int id;
    private String name;

    
    public LoginRoles() {
    }
    public LoginRoles(int id, String name) {
        this.id = id;
        this.name = name;
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

    
}
