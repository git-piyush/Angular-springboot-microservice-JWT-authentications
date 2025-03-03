package com.piyush.user.controller.admin;

import com.piyush.user.config.FeeServiceClient;
import com.piyush.user.dto.RegisteredUserDetailsResponse;
import com.piyush.user.fee.FeeClient;
import com.piyush.user.service.admin.AdminService;
import com.piyush.user.service.admin.VacancyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class UserDataController {

    @Autowired
    private FeeClient feeClient;

    @Autowired
    private FeeServiceClient feeServiceClient;

    @Autowired
    private AdminService adminService;

    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/userdetails/{userId}")
    @Transactional
    public ResponseEntity getRegisteredUserDetailsById(@PathVariable Long userId){
        RegisteredUserDetailsResponse userResponse = null;

        System.out.println(userId);

        userResponse = adminService.getRegisteredUserDetailsById(userId);

        if(userResponse!=null){
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }
}
