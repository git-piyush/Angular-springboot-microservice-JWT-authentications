package com.piyush.user.service;

import com.piyush.user.entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    UserInfo saveUserInfo(UserInfo modelRequest);

}
