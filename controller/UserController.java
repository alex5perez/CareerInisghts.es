package com.etrivium.backend.controller;

import com.etrivium.backend.controller.request.UserImageRequest;
import com.etrivium.backend.repository.entity.UserEntity;
import com.etrivium.backend.service.domain.User;
import com.etrivium.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUser(HttpServletRequest request) {
        UserEntity user = (UserEntity) request.getAttribute("user");
        return userService.getUser(user.getId());
    }

    @PostMapping("/user")
    public void saveUser(HttpServletRequest request, @RequestBody User user) throws Exception {
        UserEntity userRequest = (UserEntity) request.getAttribute("user");
        if(!Objects.equals(user.getId(), userRequest.getId())) throw new Exception("User not found");
        userService.saveUser(user);
    }

    @PostMapping("/user/image")
    public void saveUserImage(HttpServletRequest request, @RequestBody UserImageRequest userImageRequest) throws Exception {
        UserEntity user = (UserEntity) request.getAttribute("user");
        userService.saveUserImage(user.getId(), userImageRequest.getImage());
    }
}