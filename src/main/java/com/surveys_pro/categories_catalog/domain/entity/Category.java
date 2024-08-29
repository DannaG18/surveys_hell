package com.surveys_pro.categories_catalog.domain.entity;

import java.sql.Date;

public class Category {
    private int id;
    private Date createdAt;
    private Date updateAt;
    private String name;
    
    public Category() {
    }

    public Category(int id, Date createdAt, Date updateAt, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.name = name;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
