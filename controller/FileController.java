package com.etrivium.backend.controller;

import com.etrivium.backend.controller.request.GetUploadSignedUrlRequest;
import com.etrivium.backend.controller.request.SaveCourseRequest;
import com.etrivium.backend.controller.response.FileReadResponse;
import com.etrivium.backend.controller.response.GetUploadSignedUrlResponse;
import com.etrivium.backend.controller.response.UploadFileResponse;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.FileService;
import com.etrivium.backend.service.S3Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    S3Service s3Service;

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        UserEntity user = (UserEntity) request.getAttribute("user");
        return fileService.uploadFile(file, user);
    }

    @PostMapping("/upload/signed-url")
    public GetUploadSignedUrlResponse getUploadSignedUrl(HttpServletRequest request, @RequestBody GetUploadSignedUrlRequest getUploadSignedUrlRequest) throws IOException {
        UserEntity user = (UserEntity) request.getAttribute("user");
        String objectKey = "uploaded-files/" + user.getCompany().getId() + "/" + getUploadSignedUrlRequest.getFileName();
        URL url = s3Service.generateUploaddUrl(objectKey, 10);
        return GetUploadSignedUrlResponse.builder().url(url.toString()).build();
    }

    @GetMapping("/read/{fileId}")
    public FileReadResponse readFile(HttpServletRequest request, @PathVariable String fileId) {
        UserEntity user = (UserEntity) request.getAttribute("user");

        String legacyUserFilePath = "uploaded-files/" + fileId;
        String userFilePath = "uploaded-files/" + user.getId() + "/" + fileId;
        String companyFilePath = "uploaded-files/" + user.getCompany().getId() + "/" + fileId;
        try{
            URL url = s3Service.generateReadUrl(companyFilePath, 360);
            return FileReadResponse.builder().url(url.toString()).build();
        }catch (Exception ignored){
            try{
                URL url = s3Service.generateReadUrl(legacyUserFilePath, 360);
                return FileReadResponse.builder().url(url.toString()).build();
            }catch (Exception secondIgnored){
                URL url = s3Service.generateReadUrl(userFilePath, 360);
                return FileReadResponse.builder().url(url.toString()).build();
            }
        }
    }
}