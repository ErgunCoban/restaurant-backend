package com.example.restaurant.config;

import com.example.restaurant.jwt.AuthEntryPoint;
import com.example.restaurant.jwt.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthEntryPoint authEntryPoint;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //Herkese açık olan endpointler
                        .requestMatchers("/authenticate", "/refreshToken").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/restaurant/menu/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/restaurant/category/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/restaurant/order/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/restaurant/order-item/**").permitAll()

                        //Sadece adminin erişebileceği endpointler
                        .requestMatchers(HttpMethod.GET, "/api/restaurant/order/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/restaurant/order/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/restaurant/menu/**", "/api/restaurant/category/**", "/api/restaurant/table/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/restaurant/menu/**", "/api/restaurant/category/**", "/api/restaurant/table/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/restaurant/menu/**", "/api/restaurant/category/**", "/api/restaurant/table/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/restaurant/table/**").hasRole("ADMIN")

                        //Geri kalan her şey authenticate isteyecek
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
