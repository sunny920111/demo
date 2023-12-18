package com.demo.user.repository;

import com.demo.user.entity.SemesterInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<SemesterInfo, Long> {

  List<SemesterInfo> findAllByRoleId(String roleId);
}
