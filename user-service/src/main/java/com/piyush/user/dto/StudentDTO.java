package com.piyush.user.dto;

import java.util.Date;

public class StudentDTO {

    private long id;

    private String name;

    private String email;

    private String password;

    private String fatherName;

    private String motherName;

    private String studentClass;

    private Date dob;

    private String address;

    private String gender;

    public StudentDTO() {

    }

    public StudentDTO(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
}
