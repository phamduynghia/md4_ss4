package com.ra.model.dto.req;

import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDTO {
    private String username;
    private String password;
    private Set<String> roles;
}
