package com.piyush.user.repository;

import com.piyush.user.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Page<UserInfo> findByActiveAndNameContainingOrEmailContaining(String active,String name, String email,Pageable pageable);

    Page<UserInfo> findAllByActive(String active, Pageable pageable);

    Page<UserInfo> findAllByUsertype(String usertype, Pageable pageable);

    @Query("SELECT user FROM UserInfo user WHERE user.usertype = ?1 AND user.name = ?2 OR user.email = ?3")
    List<UserInfo> findTeachersByFilter(String usertype,String name,String email);

    Page<UserInfo> findByUsertypeAndNameContainingOrEmailContaining(String usertype,String name, String email,Pageable pageable);

    UserInfo findByIdAndActive(Long id, String active);
}
