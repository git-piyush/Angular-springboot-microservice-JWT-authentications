package com.piyush.student.controller;

import com.piyush.student.config.FeeServiceClient;
import com.piyush.student.dto.UserInfoRequest;
import com.piyush.student.fee.FeeClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentInfoController {

    @Autowired
    private FeeClient feeClient;

    @Autowired
    private FeeServiceClient feeServiceClient;

    @PostMapping("/student-info")
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
