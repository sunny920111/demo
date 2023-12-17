package com.demo.user.repository;

import com.demo.user.entity.SemesterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<SemesterInfo, Long> {}
