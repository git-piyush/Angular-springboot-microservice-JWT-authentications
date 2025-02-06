package com.piyush.user.controller;

import com.piyush.user.config.FeeServiceClient;
import com.piyush.user.dto.UserInfoRequest;
import com.piyush.user.entity.UserInfo;
import com.piyush.user.fee.FeeClient;
import com.piyush.user.service.UserInfoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private FeeClient feeClient;

    @Autowired
    private FeeServiceClient feeServiceClient;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/hello")
    public String hello(){
        System.out.println("Hello");
        return "hello";
    }

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
        UserInfo newUer = userInfoService.saveUserInfo(userInfo);
        return new ResponseEntity<>("User Info Saved.", HttpStatus.OK);
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
