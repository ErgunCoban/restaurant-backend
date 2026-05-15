package com.example.restaurant.dto.response;

import com.example.restaurant.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;

    private Role role;

}
