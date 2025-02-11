package com.javatechie.user;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoRequest {

     private int userid;

    private String usertype;

    private String name;

    private String email;

    private String gender;

}
