package com.demo.common.file.service.impl;

import com.demo.common.file.entity.AttachFile;
import com.demo.common.file.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
  @Override
  public AttachFile findFile(String fileId, String downloadType) {
    return null;
  }
}
