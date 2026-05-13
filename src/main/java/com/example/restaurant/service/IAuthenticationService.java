package com.example.restaurant.service;

import com.example.restaurant.dto.request.AuthRequestDTO;
import com.example.restaurant.dto.request.RefreshTokenRequest;
import com.example.restaurant.dto.response.AuthResponseDTO;
import com.example.restaurant.dto.response.UserResponseDTO;

public interface IAuthenticationService {

    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);

    AuthResponseDTO refreshToken(RefreshTokenRequest refreshTokenRequest);

}
