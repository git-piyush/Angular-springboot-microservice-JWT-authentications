package com.piyush.user.dto.settingDTOs;

public class DropDownResponse {

    private String value;

    private String label;

    public DropDownResponse() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
