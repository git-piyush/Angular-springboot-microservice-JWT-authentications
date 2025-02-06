package com.piyush.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="tbl_student")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="email",unique = true)
    private String email;

    private Date dob;

    private String phone;

    @OneToOne(mappedBy = "userInfo",fetch = FetchType.EAGER)
    private Address address;

}
