package com.demo.common.file.repository;

import com.demo.common.file.entity.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<AttachFile, String> {}
