package com.example.vkHackathon.controller;

import com.example.vkHackathon.Service.FileService;
import com.example.vkHackathon.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseDto uploadFile(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return fileService.upload(file);
    }
}
