package com.piyush.user.service.admin;

import com.piyush.user.constants.AppConstants;
import com.piyush.user.dto.StudentDTO;
import com.piyush.user.entity.UserInfo;
import com.piyush.user.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> allStudents = userInfoRepository.findAllByUsertype(AppConstants.USER_TYPE_STUDENT)
                .stream().map(user-> new StudentDTO(user.getId(), user.getName(), user.getEmail())).collect(Collectors.toList());
        return allStudents;
    }

}
