package com.ra.service.impl;

import com.ra.model.dto.req.UserLoginRequestDTO;
import com.ra.model.dto.res.UserLoginResponseDTO;
import com.ra.model.dto.req.UserRegisterRequestDTO;
import com.ra.model.dto.req.UserRequestDTO;
import com.ra.model.dto.res.UserResponseDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.UserPrinciple;
import com.ra.security.jwt.JwtProvider;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(),userLoginRequestDTO.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userPrinciple);

        return UserLoginResponseDTO.builder()
                .accessToken(token)
                .typeToken("Bearer")
                .userName(userPrinciple.getUsername())
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }

    @Override
    public void registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
       String encodedPassword = passwordEncoder.encode(userRegisterRequestDTO.getPassword());
       User user = new User();
       user.setUsername(userRegisterRequestDTO.getUsername());
       user.setPassword(encodedPassword);
       Set<Role> roles = new HashSet<>();
       Role roleName = roleRepository.findByRoleName("USER").orElseThrow();
       roles.add(roleName);
       user.setRoles(roles);
       userRepository.save(user);
    }

    @Override
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        Set<Role> roles = new HashSet<>();
        // tu cai mang ten role tu request ban len lay ve cac doi tuong role tuong ung roi day va roles
        requestDTO.getRoles().forEach(roleName->{
            Role role = roleRepository.findByRoleName(roleName).orElseThrow(()->new NoSuchElementException("Ko"));
            roles.add(role);
        });
        User user = User.builder()
                .username(requestDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(requestDTO.getPassword()))
                .status(true)
                .roles(roles)
                .build();

        User userNew = userRepository.save(user);


        return UserResponseDTO.builder()
                .id(userNew.getId())
                .username(userNew.getUsername())
                .Status(userNew.getStatus() ? "Active":"Inactive")
                .Roles(userNew.getRoles())
                .build();
    }

    @Override
    public List<UserResponseDTO> findAll() {
        List<User> list = userRepository.findAll();
        return  list.stream().map(user ->
                UserResponseDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .Status(user.getStatus()?"AC":"IN")
                        .Roles(user.getRoles()).build()
        ).collect(Collectors.toList());
    }


}