package com.comuctiva.comuctiva.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("clave_secreta_super_segura_debe_tener_al_menos_32_caracteres".getBytes());

    public String generateToken(String username, java.util.List<String> roles) {
        return Jwts.builder()
            .setSubject(username)
            .claim("roles", roles)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
            .signWith(SECRET_KEY)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public java.util.List<String> extractRoles(String token) {
        Object roles = Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("roles");
        if (roles instanceof java.util.List<?>) {
            return ((java.util.List<?>) roles).stream().map(Object::toString).toList();
        }
        return java.util.Collections.emptyList();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
