package com.demo.common.file.controller;

import com.demo.common.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

  private final FileService fileService;

  /*@GetMapping("/download/{downloadType}/{fileId}")
  public ResponseEntity<InputStreamResource> downloadFile(
      @PathVariable("downloadType") String downloadType, @PathVariable("fileId") String fileId)
      throws IOException {

    AttachFile attachFile = fileService.findFile(fileId, downloadType);

    return ResponseEntity.ok();
  }*/
}
