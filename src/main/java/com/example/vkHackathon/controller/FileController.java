package com.example.vkHackathon.controller;

import com.example.vkHackathon.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {


    @PostMapping
    public ResponseDto upload (MultipartFile file) {
        return null;
    }

}
