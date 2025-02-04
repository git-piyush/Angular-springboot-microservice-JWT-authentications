package com.piyush.student.repository;

import com.piyush.student.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {
}
