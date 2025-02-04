package com.piyush.student.service.serviceImpl;

import com.piyush.student.entity.UserInfo;
import com.piyush.student.repository.UserInfoRepository;
import com.piyush.student.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Transactional
    @Override
    public UserInfo saveUserInfo(UserInfo modelRequest) {
        UserInfo userInfo = null;
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
