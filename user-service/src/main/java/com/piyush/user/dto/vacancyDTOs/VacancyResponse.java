package com.piyush.user.dto.vacancyDTOs;

import java.util.Date;

public class VacancyResponse {

    private Long jobId;

    private String applicantType;

    private String branch;

    private String designations;

    private String specialization;

    private Date startDate;

    private Date endDate;

    private Integer noOfVacancy;

    private String refCodeCourse;

    public VacancyResponse() {
    }

    public VacancyResponse(Long jobId, String applicantType, String branch, String designations, String specialization, Date startDate, Date endDate, Integer noOfVacancy, String refCodeCourse) {
        this.jobId = jobId;
        this.applicantType = applicantType;
        this.branch = branch;
        this.designations = designations;
        this.specialization = specialization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noOfVacancy = noOfVacancy;
        this.refCodeCourse = refCodeCourse;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getRefCodeCourse() {
        return refCodeCourse;
    }

    public void setRefCodeCourse(String refCodeCourse) {
        this.refCodeCourse = refCodeCourse;
    }

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
