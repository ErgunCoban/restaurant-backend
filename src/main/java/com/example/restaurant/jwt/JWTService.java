package com.example.restaurant.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getKey(){
        byte[] bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }


    public <T> T exportToken(String token, Function<Claims, T> claimsFunction){
        Claims claims = getClaims(token);
        return  claimsFunction.apply(claims);
    }



    public Claims getClaims( String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    public String getUsernameByToken( String token){
        return exportToken(token, Claims::getSubject);
    }

    public Boolean isTokenValid(String token ){
        Date expiredDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expiredDate);
    }




}