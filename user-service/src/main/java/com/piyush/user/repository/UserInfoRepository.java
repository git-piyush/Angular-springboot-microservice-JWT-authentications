package com.piyush.user.repository;

import com.piyush.user.entity.UserInfo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    List<UserInfo> findAllByUsertype(String usertype);

    @Query("SELECT user FROM UserInfo user WHERE user.usertype = ?1 AND user.name = ?2 OR user.email = ?3")
    List<UserInfo> findTeachersByFilter(String usertype,String name,String email);

    List<UserInfo> findByUsertypeAndNameContainingOrEmailContaining(String usertype,String name, String email);

}
