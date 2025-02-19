package com.piyush.user.service.admin;

import com.piyush.user.constants.AppConstants;
import com.piyush.user.dto.*;
import com.piyush.user.entity.UserInfo;
import com.piyush.user.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public RegisteredUserResponse findByActiveAndNameContainingOrEmailContaining(int pageNo, int pageSize, String sortBy, String sortDir, String filterType, String statusSubfilter, String filterText) {

        String name=null;// = filterText;
        String email=null;// = filterText;

        if(filterText != null && filterType.equalsIgnoreCase("name")){
            name = filterText;
        } else if (filterText != null && filterType.equalsIgnoreCase("email")) {
            email = filterText;
        }

        Sort sort = sortDir.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<UserInfo> page = null;
        if(filterType==null || filterType=="" || filterType.isEmpty()){
            page = userInfoRepository.findAllByActive(AppConstants.HOLD,pageable);
        }else{
            page = userInfoRepository.findByActiveAndNameContainingOrEmailContaining(AppConstants.HOLD,name,email,pageable);
        }

        //get the content from page
        List<UserInfo> userList = page.getContent();
        List<RegisteredUserDTO> allUsers = userList.stream().map(user-> new RegisteredUserDTO(user.getId(), user.getName(), user.getEmail(), user.getDoj(),user.getActive(), user.getUsertype())).collect(Collectors.toList());

        RegisteredUserResponse res = new RegisteredUserResponse();
        res.setContent(allUsers);
        res.setPageNo(page.getNumber());
        res.setPageSize(page.getSize());
        res.setLast(page.isLast());
        res.setTotalElements(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        return res;
    }

    @Override
    public AllStudentsResponse getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir, String filterType, String statusSubfilter, String filterText) {

        String name = filterText;
        String email = filterText;

        Sort sort = sortDir.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<UserInfo> page = null;
        if(filterType==null){
             page = userInfoRepository.findAllByUsertype(AppConstants.USER_TYPE_STUDENT,pageable);
        }else{
             page = userInfoRepository.findByUsertypeAndNameContainingOrEmailContaining(AppConstants.USER_TYPE_STUDENT,name,email,pageable);
        }

        //get the content from page
        List<UserInfo> studentList = page.getContent();
        List<StudentDTO> allStudents = studentList.stream().map(user-> new StudentDTO(user.getId(), user.getName(), user.getEmail(), user.getDoj(), user.getActive())).collect(Collectors.toList());

        AllStudentsResponse res = new AllStudentsResponse();
        res.setContent(allStudents);
        res.setPageNo(page.getNumber());
        res.setPageSize(page.getSize());
        res.setLast(page.isLast());
        res.setTotalElements(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        return res;
    }

    @Override
    public AllTeachersResponse getAllTeacher(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<UserInfo> page = userInfoRepository.findAllByUsertype(AppConstants.USER_TYPE_TEACHER,pageable);

        //get the content from page
        List<UserInfo> teacherList = page.getContent();

        List<TeacherDTO> allTeachers = teacherList.stream().map(user-> new TeacherDTO(user.getId(), user.getName(), user.getEmail(),
                user.getDoj(), user.getActive())).collect(Collectors.toList());

        AllTeachersResponse res = new AllTeachersResponse();
        res.setContent(allTeachers);
        res.setPageNo(page.getNumber());
        res.setPageSize(page.getSize());
        res.setLast(page.isLast());
        res.setTotalElements(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        return res;
    }

    @Override
    public AllTeachersResponse getTeachersByFilter(int pageNo, int pageSize, String sortBy, String sortDir,TeacherFilterRequest teacherFilterRequest) {
        String name = null;
        String email = null;
        if(teacherFilterRequest.getFilterText() != null && teacherFilterRequest.getFilterType().equalsIgnoreCase("name")){
            name = teacherFilterRequest.getFilterText();
        } else if (teacherFilterRequest.getFilterText() != null && teacherFilterRequest.getFilterType().equalsIgnoreCase("email")) {
            email = teacherFilterRequest.getFilterText();
        }

        Sort sort = sortDir.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<UserInfo> page = userInfoRepository.findByUsertypeAndNameContainingOrEmailContaining(AppConstants.USER_TYPE_TEACHER,name,email,pageable);

        List<UserInfo> teacherList = page.getContent();

        List<TeacherDTO> allTeachers = teacherList.stream().map(user-> new TeacherDTO(user.getId(), user.getName(), user.getEmail(), user.getDoj(), user.getActive())).collect(Collectors.toList());

        AllTeachersResponse res = new AllTeachersResponse();
        res.setContent(allTeachers);
        res.setPageNo(page.getNumber());
        res.setPageSize(page.getSize());
        res.setLast(page.isLast());
        res.setTotalElements(page.getTotalElements());
        res.setTotalPages(page.getTotalPages());
        return res;
    }

    @Override
    public RegisteredUserDetailsResponse getRegisteredUserDetailsById(Long id) {
        UserInfo user = userInfoRepository.findByIdAndActive(id, AppConstants.HOLD);

        RegisteredUserDetailsResponse res = new RegisteredUserDetailsResponse(
                user.getId(),user.getUserid(),user.getName()
        );
        return res;
    }
}
