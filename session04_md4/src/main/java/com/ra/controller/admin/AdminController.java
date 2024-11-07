package com.ra.controller.admin;

import com.ra.model.dto.req.UserRequestDTO;
import com.ra.model.dto.res.UserResponseDTO;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AuthService authService;
    @GetMapping
    public ResponseEntity<?> index (){
        return new ResponseEntity<>("xin chào ad" , HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/auth")
    public ResponseEntity<UserResponseDTO> createAccount (@RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(authService.create(userRequestDTO),HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SUB_ADMIN')")
    @GetMapping("/auth")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(authService.findAll(),HttpStatus.OK);
    }
}
