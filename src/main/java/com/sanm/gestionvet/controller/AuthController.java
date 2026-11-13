package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.dto.LoginRequest;
import com.sanm.gestionvet.dto.LoginResponse;
import com.sanm.gestionvet.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticación", description = "Inicio de sesión y obtención del token JWT")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Inicia sesión",
            description = "Recibe email y contraseña y retorna un token JWT para usar en el resto de la API")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
