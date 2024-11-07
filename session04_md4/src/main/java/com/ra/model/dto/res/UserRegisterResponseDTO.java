package com.ra.model.dto.res;

import com.ra.model.entity.Role;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterResponseDTO {
    private String userName;
    private String accessToken;
    private String typeToken;
    private Set<Role> roles;
}
