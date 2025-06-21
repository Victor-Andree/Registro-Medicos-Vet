package webvet.v1.infraestructure.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webvet.v1.application.dto.request.LoginRequest;
import webvet.v1.application.dto.response.AuthenticationResponse;
import webvet.v1.application.useCase.auth.LoginUseCase;
import webvet.v1.infraestructure.services.TokenBlacklistService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/authentication")
public class Auth {

    private final LoginUseCase loginUseCase;

    private final TokenBlacklistService tokenBlacklistService;

    public Auth(LoginUseCase loginUseCase, TokenBlacklistService tokenBlacklistService) {
        this.loginUseCase = loginUseCase;
        this.tokenBlacklistService = tokenBlacklistService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null ||
                loginRequest.getUsername().isBlank() || loginRequest.getPassword().isBlank()) {

            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Debes ingresar un usuario y una contraseña válidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        AuthenticationResponse response = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.addToBlacklist(token);
        }
        return ResponseEntity.ok("Sesión cerrada. Token invalidado.");

    }

}
