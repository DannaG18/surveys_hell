package com.surveys_pro.subresponse_options.domain.entity;

import java.sql.Date;

public class SubresponseOptions {
    private int id;
    private int subresponseNumber;
    private Date createAt;
    private int responseOptionsId;
    private Date updateAt;
    private String componentHtml;
    private String subresponseText;
    
    public SubresponseOptions() {
    }

    public SubresponseOptions(int id, int subresponseNumber, Date createAt, int responseOptionsId, Date updateAt,
            String componentHtml, String subresponseText) {
        this.id = id;
        this.subresponseNumber = subresponseNumber;
        this.createAt = createAt;
        this.responseOptionsId = responseOptionsId;
        this.updateAt = updateAt;
        this.componentHtml = componentHtml;
        this.subresponseText = subresponseText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsubresponseNumber() {
        return subresponseNumber;
    }

    public void setsubresponseNumber(int subresponseNumber) {
        this.subresponseNumber = subresponseNumber;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getResponseOptionsId() {
        return responseOptionsId;
    }

    public void setResponseOptionsId(int responseOptionsId) {
        this.responseOptionsId = responseOptionsId;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getComponentHtml() {
        return componentHtml;
    }

    public void setComponentHtml(String componentHtml) {
        this.componentHtml = componentHtml;
    }

    public String getSubresponseText() {
        return subresponseText;
    }

    public void setSubresponseText(String subresponseText) {
        this.subresponseText = subresponseText;
    }
}
