package com.ra.service;

import com.ra.model.dto.req.UserLoginRequestDTO;
import com.ra.model.dto.res.UserLoginResponseDTO;
import com.ra.model.dto.req.UserRegisterRequestDTO;
import com.ra.model.dto.req.UserRequestDTO;
import com.ra.model.dto.res.UserResponseDTO;

import java.util.List;

public interface AuthService {
    UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);
    void registerUser(UserRegisterRequestDTO UserRegisterRequestDTO);
    UserResponseDTO create(UserRequestDTO userRequestDTO);
    List<UserResponseDTO> findAll();
}
