package com.piyush.user.dto;

import lombok.Getter;
import lombok.Setter;

public class TeacherFilterRequest {

    private String filterType;

    private String filterText;

    private String statusSubfilter;

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }

    public String getStatusSubfilter() {
        return statusSubfilter;
    }

    public void setStatusSubfilter(String statusSubfilter) {
        this.statusSubfilter = statusSubfilter;
    }
}
