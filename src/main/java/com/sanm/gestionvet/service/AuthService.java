package com.sanm.gestionvet.service;

import com.sanm.gestionvet.dto.LoginRequest;
import com.sanm.gestionvet.dto.LoginResponse;
import com.sanm.gestionvet.model.Credencial;
import com.sanm.gestionvet.repository.CredencialRepository;
import com.sanm.gestionvet.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {

        Credencial credencial = credencialRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getContrasenia(), credencial.getContrasenia())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(credencial.getEmail());
        return new LoginResponse(token, "Bearer");
    }
}
