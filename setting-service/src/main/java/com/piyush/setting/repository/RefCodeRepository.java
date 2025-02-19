package com.piyush.setting.repository;

import com.piyush.setting.entity.RefCode;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefCodeRepository extends JpaRepository<RefCode, String> {


    Page<RefCode> findAllByActive(String yes, Pageable pageable);

    Page<RefCode> findByRefCode(String filterText, Pageable pageable);

    Page<RefCode> findByCategory(String filterText, Pageable pageable);

    Page<RefCode> findByLongNameContaining(String filterText, Pageable pageable);

    Page<RefCode> findByActive(String filterText, Pageable pageable);

    List<RefCode> findByCategory(String category);

    @Query("select distinct category from RefCode")
    List<String> findDistinctRefCodeByCategoryColumn();
}
