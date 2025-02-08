package com.piyush.user.service.admin;

import com.piyush.user.constants.AppConstants;
import com.piyush.user.dto.StudentDTO;
import com.piyush.user.dto.TeacherDTO;
import com.piyush.user.dto.TeacherFilterRequest;
import com.piyush.user.repository.UserInfoRepository;
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

    @Override
    public List<TeacherDTO> getAllTeacher() {
        List<TeacherDTO> allTeachers = userInfoRepository.findAllByUsertype(AppConstants.USER_TYPE_TEACHER)
                .stream().map(user-> new TeacherDTO(user.getId(), user.getName(), user.getEmail())).collect(Collectors.toList());
        return allTeachers;
    }

    @Override
    public List<TeacherDTO> getTeachersByFilter(TeacherFilterRequest teacherFilterRequest) {
        String name = null;
        String email = null;
        if(teacherFilterRequest.getFilterText() != null && teacherFilterRequest.getFilterType().equalsIgnoreCase("name")){
            name = teacherFilterRequest.getFilterText();
        } else if (teacherFilterRequest.getFilterText() != null && teacherFilterRequest.getFilterType().equalsIgnoreCase("email")) {
            email = teacherFilterRequest.getFilterText();
        }
        List<TeacherDTO> allTeachers = userInfoRepository.findByUsertypeAndNameContainingOrEmailContaining(AppConstants.USER_TYPE_TEACHER,name, email )
                .stream().map(user-> new TeacherDTO(user.getId(), user.getName(), user.getEmail())).collect(Collectors.toList());
        return allTeachers;
    }

}
