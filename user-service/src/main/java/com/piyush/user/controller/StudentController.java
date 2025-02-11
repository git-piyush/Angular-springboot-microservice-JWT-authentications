package com.piyush.user.controller;

import com.piyush.user.config.FeeServiceClient;
import com.piyush.user.constants.AppConstants;
import com.piyush.user.dto.UserInfoRequest;
import com.piyush.user.entity.UserInfo;
import com.piyush.user.fee.FeeClient;
import com.piyush.user.service.admin.AdminService;
import com.piyush.user.service.student.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private FeeClient feeClient;

    @Autowired
    private FeeServiceClient feeServiceClient;

    @Autowired
    private StudentService studentService;

    //This method will also get called from identity service
    //to store the basic details of user in TBL_USER
    @PostMapping("/user-info")
    @Transactional
    public ResponseEntity saveUserDetails(@RequestBody UserInfoRequest modelRequest){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(modelRequest.getUserid());
        userInfo.setEmail(modelRequest.getEmail());
        userInfo.setName(modelRequest.getName());
        userInfo.setUsertype(modelRequest.getUsertype());
        userInfo.setGender(modelRequest.getGender());
        userInfo.setActive(AppConstants.NO);
        userInfo.setDoj(null); //since this method will get called from identity service during registration, and that time doj will be bull
        UserInfo newUer = studentService.saveUserInfo(userInfo);
        return new ResponseEntity<>("User Info Saved.", HttpStatus.OK);
    }
}
