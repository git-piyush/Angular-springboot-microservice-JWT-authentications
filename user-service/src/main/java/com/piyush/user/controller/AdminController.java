package com.piyush.user.controller;

import com.piyush.user.config.FeeServiceClient;
import com.piyush.user.constants.AppConstants;
import com.piyush.user.dto.*;
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

    @GetMapping("/registereduser")
    @Transactional
    public ResponseEntity findAllusersByActiveAndNameContainingOrEmailContaining(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir,
                                         @RequestParam(value = "filterType", required = false) String filterType,
                                         @RequestParam(value = "statusSubfilter", required = false) String statusSubfilter,
                                         @RequestParam(value = "filterText", required = false) String filterText){

        AllStudentsResponse allStudent = adminService.findByActiveAndNameContainingOrEmailContaining(pageNo, pageSize, sortBy, sortDir, filterType,statusSubfilter,filterText);
        if(allStudent.getContent()!=null && !allStudent.getContent().isEmpty()){
            return new ResponseEntity<>(allStudent, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/students")
    @Transactional
    public ResponseEntity getAllStudents(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir,
                                         @RequestParam(value = "filterType", required = false) String filterType,
                                         @RequestParam(value = "statusSubfilter", required = false) String statusSubfilter,
                                         @RequestParam(value = "filterText", required = false) String filterText){


        AllStudentsResponse allStudent = adminService.getAllStudent(pageNo, pageSize, sortBy, sortDir, filterType,statusSubfilter,filterText);
        if(allStudent.getContent()!=null && !allStudent.getContent().isEmpty()){
            return new ResponseEntity<>(allStudent, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/teachers")
    @Transactional
    public ResponseEntity getAllTeachers(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir){
        AllTeachersResponse allTeacher = adminService.getAllTeacher(pageNo, pageSize, sortBy, sortDir);
        if(allTeacher.getContent()!=null && !allTeacher.getContent().isEmpty()){
            return new ResponseEntity<>(allTeacher, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/teachers")
    @Transactional
    public ResponseEntity getTeachersByFilter(@RequestBody TeacherFilterRequest teacherFilterRequest,
                                              @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                              @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir){
        AllTeachersResponse allTeacher = null;
        if((teacherFilterRequest.getFilterType()==null && teacherFilterRequest.getFilterText()==null &&
                teacherFilterRequest.getStatusSubfilter()==null)||(teacherFilterRequest.getFilterType().isBlank() && teacherFilterRequest.getFilterText().isBlank() &&
                teacherFilterRequest.getStatusSubfilter().isBlank())){
            allTeacher = adminService.getAllTeacher(pageNo,pageSize,"id","asc");
        }else{
            allTeacher = adminService.getTeachersByFilter(pageNo, pageSize, sortBy, sortDir,teacherFilterRequest);
        }
        if(allTeacher!=null && !allTeacher.getContent().isEmpty()){
            return new ResponseEntity<>(allTeacher, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found.", HttpStatus.NOT_FOUND);
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
