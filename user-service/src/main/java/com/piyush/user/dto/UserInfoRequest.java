package com.piyush.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserInfoRequest {

    private Long id;

    private String name;

    private String email;

    private Date dob;

    private String phone;

}
