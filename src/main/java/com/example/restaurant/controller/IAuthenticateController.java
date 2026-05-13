package com.example.restaurant.controller;

import com.example.restaurant.dto.request.AuthRequestDTO;
import com.example.restaurant.dto.request.RefreshTokenRequest;
import com.example.restaurant.dto.response.AuthResponseDTO;
import com.example.restaurant.model.base.RootEntity;

public interface IAuthenticateController {

    public RootEntity<AuthResponseDTO> authenticate(AuthRequestDTO authRequestDTO);

    public RootEntity<AuthResponseDTO> refreshToken(RefreshTokenRequest refreshTokenRequest);

}
