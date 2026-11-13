package com.sanm.gestionvet.service;

import com.sanm.gestionvet.dto.LoginRequest;
import com.sanm.gestionvet.dto.LoginResponse;
import com.sanm.gestionvet.model.Credencial;
import com.sanm.gestionvet.repository.CredencialRepository;
import com.sanm.gestionvet.security.JwtUtil;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private final Faker faker = new Faker();

    @Mock
    private CredencialRepository credencialRepository;

    // Estos dos son reales (no falsos) porque no necesitan base de datos
    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Spy
    private JwtUtil jwtUtil = new JwtUtil("clave-de-prueba-para-jwt-con-mas-de-32-caracteres", 3600000);

    @InjectMocks
    private AuthService authService;

    private Credencial crearCredencial(String email, String clave) {
        Credencial credencial = new Credencial();
        credencial.setNombre(faker.name().firstName());
        credencial.setEmail(email);
        credencial.setContrasenia(passwordEncoder.encode(clave));
        return credencial;
    }

    @Test
    void loginCorrectoEntregaToken() {
        String email = faker.internet().emailAddress();
        String clave = faker.internet().password();
        when(credencialRepository.findByEmail(email))
                .thenReturn(Optional.of(crearCredencial(email, clave)));

        LoginResponse respuesta = authService.login(new LoginRequest(email, clave));

        assertNotNull(respuesta.getToken());
        assertEquals("Bearer", respuesta.getTipo());
        assertTrue(jwtUtil.isTokenValido(respuesta.getToken()));
        assertEquals(email, jwtUtil.getEmail(respuesta.getToken()));
    }

    @Test
    void loginConClaveIncorrectaFalla() {
        String email = faker.internet().emailAddress();
        when(credencialRepository.findByEmail(email))
                .thenReturn(Optional.of(crearCredencial(email, "clave-correcta")));

        assertThrows(ResponseStatusException.class,
                () -> authService.login(new LoginRequest(email, "clave-incorrecta")));
    }

    @Test
    void loginConEmailInexistenteFalla() {
        String email = faker.internet().emailAddress();
        when(credencialRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> authService.login(new LoginRequest(email, "cualquiera")));
    }
}
