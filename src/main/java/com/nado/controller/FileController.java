package com.nado.controller;

import com.nado.dto.EntityResponse;
import com.nado.dto.FileDto;
import com.nado.model.File;
import com.nado.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping()
    public byte[] downloadFile(@RequestBody File file) {
        return fileService.downloadFile(file);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityResponse<FileDto>> getFileMetaData(@PathVariable Long id) {
        FileDto fileMetaData = fileService.getFileMetaData(id);
        return  ResponseEntity.ok(new EntityResponse<>(fileMetaData));
    }
}
