package com.piyush.user.controller;

import com.piyush.user.config.FeeServiceClient;
import com.piyush.user.dto.StudentDTO;
import com.piyush.user.dto.TeacherDTO;
import com.piyush.user.dto.UserInfoRequest;
import com.piyush.user.fee.FeeClient;
import com.piyush.user.service.admin.AdminService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private FeeClient feeClient;

    @Autowired
    private FeeServiceClient feeServiceClient;

    @Autowired
    private AdminService adminService;

    @GetMapping("/students")
    @Transactional
    public ResponseEntity getAllStudents(){
        List<StudentDTO> allStudent = adminService.getAllStudent();
        System.out.println(allStudent.size());
        if(allStudent!=null && !allStudent.isEmpty()){
            return new ResponseEntity<List<StudentDTO>>(allStudent, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/teachers")
    @Transactional
    public ResponseEntity getAllTeachers(){
        List<TeacherDTO> allTeacher = adminService.getAllTeacher();
        System.out.println(allTeacher.size());
        if(allTeacher!=null && !allTeacher.isEmpty()){
            return new ResponseEntity<List<TeacherDTO>>(allTeacher, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/user-details")
    @Transactional
    public String saveUserInfo(@Valid @RequestBody UserInfoRequest modelRequest,
                               @RequestHeader(name="Authorization") String token){
        System.out.println(token);
        String msg = null;
        String msg2 = null;
        msg2 = feeClient.findStudentFeeDetails("1", token);
        msg = feeServiceClient.findStudentFeeDetails(String.valueOf(1));

        return "saveUserInfo"+" "+msg+" "+msg2;
    }

}
