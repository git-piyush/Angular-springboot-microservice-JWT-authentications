package com.piyush.setting.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class RefCodeModelRequest {
    private String refCode;
    private String category;
    private String longName;
    private String active;

    public RefCodeModelRequest() {
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
