package com.piyush.student.service;

import com.piyush.student.entity.StudentInfo;
import org.springframework.stereotype.Service;

@Service
public interface StudentInfoService {

    StudentInfo saveStudentInfo(StudentInfo modelRequest);

}
