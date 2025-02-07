package com.piyush.user.service.student;

import com.piyush.user.entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    UserInfo saveUserInfo(UserInfo modelRequest);
}
