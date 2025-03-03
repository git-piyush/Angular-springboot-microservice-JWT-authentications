package com.piyush.user.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tbl_reference_code")
public class RefCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="REF_CODE", nullable = false, updatable = false)
    private String refCode;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="REF_CODE_LONG_NAME")
    private String longName;

    @Column(name="FLG_ACTIVE")
    private String active;

    @Column(name="MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDateTime;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name="CREATED_BY")
    private String createdBy;

//    @PreUpdate
//    @PrePersist
//    public void updateTimeStamps() {
//
//    }


    public RefCode() {
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

    public Date getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Date modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
