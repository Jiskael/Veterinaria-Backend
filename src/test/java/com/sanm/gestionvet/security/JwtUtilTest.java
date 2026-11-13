package com.sanm.gestionvet.security;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final Faker faker = new Faker();
    private final String secret = "clave-de-prueba-para-jwt-con-mas-de-32-caracteres";

    @Test
    void generaTokenYRecuperaElEmail() {
        JwtUtil jwtUtil = new JwtUtil(secret, 3600000);
        String email = faker.internet().emailAddress();

        String token = jwtUtil.generateToken(email);

        assertTrue(jwtUtil.isTokenValido(token));
        assertEquals(email, jwtUtil.getEmail(token));
    }

    @Test
    void tokenInventadoNoEsValido() {
        JwtUtil jwtUtil = new JwtUtil(secret, 3600000);

        assertFalse(jwtUtil.isTokenValido(faker.lorem().characters(60)));
    }

    @Test
    void tokenExpiradoNoEsValido() {
        // expiración negativa = el token nace vencido
        JwtUtil jwtUtil = new JwtUtil(secret, -1000);

        String token = jwtUtil.generateToken(faker.internet().emailAddress());

        assertFalse(jwtUtil.isTokenValido(token));
    }

    @Test
    void tokenFirmadoConOtraClaveNoEsValido() {
        JwtUtil emisor = new JwtUtil("otra-clave-distinta-con-mas-de-32-caracteres-123", 3600000);
        JwtUtil validador = new JwtUtil(secret, 3600000);

        String token = emisor.generateToken(faker.internet().emailAddress());

        assertFalse(validador.isTokenValido(token));
    }
}
