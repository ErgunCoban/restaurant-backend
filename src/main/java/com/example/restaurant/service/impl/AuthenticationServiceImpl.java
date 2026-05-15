package com.example.restaurant.service.impl;

import com.example.restaurant.dto.request.AuthRequestDTO;
import com.example.restaurant.dto.request.RefreshTokenRequest;
import com.example.restaurant.dto.response.AuthResponseDTO;
import com.example.restaurant.exception.BaseException;
import com.example.restaurant.exception.ErrorMessage;
import com.example.restaurant.exception.MessageType;
import com.example.restaurant.jwt.JWTService;
import com.example.restaurant.model.RefreshToken;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.RefreshTokenRepository;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;
    private final JWTService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optionalUser = userRepository.findByEmail(authRequestDTO.getEmail());

            String accessToken = jwtService.generateToken(optionalUser.get());

            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optionalUser.get()));

            return new AuthResponseDTO(accessToken, savedRefreshToken.getRefreshToken(), optionalUser.get().getRole());
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INCORRECT, e.getMessage()));
        }
    }

    public boolean isValidRefreshToken(Date expiredDate){
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponseDTO refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken());
        if (optionalRefreshToken.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshTokenRequest.getRefreshToken()));
        }

        if (!isValidRefreshToken(optionalRefreshToken.get().getExpiredDate())){
            throw new BaseException(new ErrorMessage(MessageType.REFESH_TOKEN_IS_EXPIRED, refreshTokenRequest.getRefreshToken()));
        }

        User user = optionalRefreshToken.get().getUser();
        refreshTokenRepository.delete(optionalRefreshToken.get());
        String accessToken = jwtService.generateToken(user);
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

        return new AuthResponseDTO(accessToken, savedRefreshToken.getRefreshToken(), user.getRole());
    }

}
