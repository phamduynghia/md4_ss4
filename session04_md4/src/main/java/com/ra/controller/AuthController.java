package com.ra.controller;

import com.ra.model.dto.req.UserLoginRequestDTO;
import com.ra.model.dto.res.UserLoginResponseDTO;
import com.ra.model.dto.req.UserRegisterRequestDTO;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
return new ResponseEntity<>(authService.login(userLoginRequestDTO), HttpStatus.OK);
    }

@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        authService.registerUser(userRegisterRequestDTO);
        return new ResponseEntity<>("ok",HttpStatus.OK);
}
}
