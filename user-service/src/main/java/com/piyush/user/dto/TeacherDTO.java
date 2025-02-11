package com.piyush.user.dto;

import java.util.Date;

public class TeacherDTO {

    private long id;

    private String name;

    private String email;

    private String designation;

    private Date doj;

    private String status;

    public TeacherDTO() {

    }

    public TeacherDTO(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public TeacherDTO(long id, String name, String email, Date doj, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.doj = doj;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
