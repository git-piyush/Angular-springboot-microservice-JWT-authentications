package com.piyush.user.service.admin;

import com.piyush.user.dto.*;
import com.piyush.user.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    AllStudentsResponse getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir);

    AllTeachersResponse getAllTeacher(int pageNo, int pageSize, String sortBy, String sortDir);

    AllTeachersResponse getTeachersByFilter(int pageNo, int pageSize, String sortBy, String sortDir,TeacherFilterRequest teacherFilterRequest);
}
