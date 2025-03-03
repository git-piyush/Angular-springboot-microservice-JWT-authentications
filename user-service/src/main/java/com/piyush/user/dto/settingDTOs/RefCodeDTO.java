package com.piyush.user.dto.settingDTOs;

public class RefCodeDTO {
    private String refCode;
    private String category;
    private String longName;
    private String active;

    public RefCodeDTO() {
    }

    public RefCodeDTO(String refCode, String category, String longName, String active) {
        this.refCode = refCode;
        this.category = category;
        this.longName = longName;
        this.active = active;
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
