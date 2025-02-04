package com.piyush.student.service.serviceImpl;

import com.piyush.student.entity.StudentInfo;
import com.piyush.student.repository.StudentInfoRepository;
import com.piyush.student.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoRepository userInfoRepository;

    @Transactional
    @Override
    public StudentInfo saveStudentInfo(StudentInfo modelRequest) {
        StudentInfo userInfo = null;
        try {
            if(modelRequest!=null){
                return userInfoRepository.save(modelRequest);
            }
        } catch (Exception e) {
            //throw new BusinessException("Exception In Saving User.");
        }
        return userInfo;
    }
}
