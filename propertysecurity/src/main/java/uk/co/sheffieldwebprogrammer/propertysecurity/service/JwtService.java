package uk.co.sheffieldwebprogrammer.propertysecurity.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    void addClaim(String key, String value);
}