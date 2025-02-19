package com.piyush.user.service.admin;

import com.piyush.user.dto.*;
import com.piyush.user.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    RegisteredUserResponse findByActiveAndNameContainingOrEmailContaining(int pageNo, int pageSize, String sortBy, String sortDir, String filterType, String statusSubfilter, String filterText);

    AllStudentsResponse getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir, String filterType, String statusSubfilter, String filterText);

    AllTeachersResponse getAllTeacher(int pageNo, int pageSize, String sortBy, String sortDir);

    AllTeachersResponse getTeachersByFilter(int pageNo, int pageSize, String sortBy, String sortDir,TeacherFilterRequest teacherFilterRequest);

    RegisteredUserDetailsResponse getRegisteredUserDetailsById(Long userId);

}
