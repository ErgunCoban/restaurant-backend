package com.example.restaurant.controller.impl;

import com.example.restaurant.controller.IAuthenticateController;
import com.example.restaurant.controller.base.RestBaseController;
import com.example.restaurant.dto.request.AuthRequestDTO;
import com.example.restaurant.dto.request.RefreshTokenRequest;
import com.example.restaurant.dto.response.AuthResponseDTO;
import com.example.restaurant.model.base.RootEntity;
import com.example.restaurant.service.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticateControllerImpl extends RestBaseController implements IAuthenticateController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        return ok(authenticationService.authenticate(authRequestDTO));
    }

    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponseDTO> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
