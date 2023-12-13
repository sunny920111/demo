package com.demo.common.file.service;

import com.demo.common.file.entity.AttachFile;

public interface FileService {
  AttachFile findFile(String fileId, String downloadType);
}
