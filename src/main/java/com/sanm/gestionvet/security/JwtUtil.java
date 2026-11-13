package com.sanm.gestionvet.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

// Crea y valida los tokens JWT
@Component
public class JwtUtil {

    private final SecretKey key;
    private final long expiracion;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expiracion) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiracion = expiracion;
    }

    // Genera un token con el email dentro (subject) y una fecha de expiración
    public String generateToken(String email) {
        Date ahora = new Date();
        return Jwts.builder()
                .subject(email)
                .issuedAt(ahora)
                .expiration(new Date(ahora.getTime() + expiracion))
                .signWith(key)
                .compact();
    }

    // Saca el email que viene dentro del token
    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Un token es válido si la firma coincide y no ha expirado
    public boolean isTokenValido(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
