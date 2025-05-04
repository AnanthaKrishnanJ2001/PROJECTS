package com.rentvideo.RentVideo.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JWTService {
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> additionalClaims, UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
    String extractUserName(String token);
    Date extractExpirationDate(String token);
    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);
    Claims extractAllClaims(String token);
    Key getSignInKey();
}
