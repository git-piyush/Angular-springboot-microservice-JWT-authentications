package com.piyush.user.service.student;

import com.piyush.user.entity.UserInfo;
import com.piyush.user.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Transactional
    @Override
    public UserInfo saveUserInfo(UserInfo modelRequest) {
        UserInfo userInfo = null;
        try {
            if(modelRequest!=null){
                userInfo = userInfoRepository.save(modelRequest);
            }
        } catch (Exception e) {
            //throw new BusinessEx("Exception In Saving User.");
        }
        return userInfo;
    }
}
