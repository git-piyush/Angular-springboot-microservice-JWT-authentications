package com.piyush.user.serviceImpl;

import com.piyush.user.entity.UserInfo;
import com.piyush.user.repository.StudentInfoRepository;
import com.piyush.user.service.UserInfoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private StudentInfoRepository userInfoRepository;

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
