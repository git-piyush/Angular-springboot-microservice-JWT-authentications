package com.piyush.user.service.admin;

import com.piyush.user.dto.StudentDTO;
import com.piyush.user.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    List<StudentDTO> getAllStudent();

}
