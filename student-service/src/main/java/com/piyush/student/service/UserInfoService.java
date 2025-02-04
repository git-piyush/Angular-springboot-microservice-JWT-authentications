package com.piyush.student.service;

import com.piyush.student.entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    UserInfo saveUserInfo(UserInfo modelRequest);

}
