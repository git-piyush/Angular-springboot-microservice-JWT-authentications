package com.piyush.student.entity;

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
public class StudentInfo {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="email",unique = true)
    private String email;

    private Date dob;

    private String phone;

    @OneToOne(mappedBy = "studentInfo",fetch = FetchType.EAGER)
    private Address address;

}
