package com.ra.model.dto.req;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterRequestDTO {
    private String username;
    private String password;
}
