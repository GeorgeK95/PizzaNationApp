package com.example.demo.controller;

import com.example.demo.util.CloudUploader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadController {

    @PostMapping("/upload")
    public String uploadProcess(@RequestParam("file") MultipartFile image) throws IOException {
        CloudUploader.uploadImage(image);

        return "redirect:/";
    }


}
