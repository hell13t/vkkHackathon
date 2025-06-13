package com.example.vkHackathon.controller;

import com.example.vkHackathon.Service.FileService;
import com.example.vkHackathon.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseDto uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return fileService.upload(file);
    }
}
