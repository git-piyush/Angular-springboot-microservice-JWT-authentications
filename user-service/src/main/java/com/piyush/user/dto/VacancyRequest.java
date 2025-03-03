package com.piyush.user.dto;

import java.util.Date;

public class VacancyRequest {

    private String applicantType;

    private String branch;

    private String designations;

    private String specialization;

    private Date startDate;

    private Date endDate;

    private Integer noOfVacancy;

    public String getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(String applicantType) {
        this.applicantType = applicantType;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDesignations() {
        return designations;
    }

    public void setDesignations(String designations) {
        this.designations = designations;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getNoOfVacancy() {
        return noOfVacancy;
    }

    public void setNoOfVacancy(Integer noOfVacancy) {
        this.noOfVacancy = noOfVacancy;
    }
}
