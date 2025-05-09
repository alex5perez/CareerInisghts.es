package com.etrivium.backend.controller;

import com.etrivium.backend.controller.request.SignUpRequest;
import com.etrivium.backend.controller.request.SigninKeyRequest;
import com.etrivium.backend.controller.request.SigninRequest;
import com.etrivium.backend.controller.response.JwtAuthenticationResponse;
import com.etrivium.backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/signin/key")
    public ResponseEntity<JwtAuthenticationResponse> signinKey(@RequestBody SigninKeyRequest request) {
        return ResponseEntity.ok(authenticationService.signinKey(request));
    }
}