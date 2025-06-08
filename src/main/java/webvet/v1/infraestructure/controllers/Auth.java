package webvet.v1.infraestructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webvet.v1.application.dto.request.LoginRequest;
import webvet.v1.application.dto.response.AuthenticationResponse;
import webvet.v1.application.useCase.auth.LoginUseCase;

@RestController
@RequestMapping("/api/v1/authentication")
public class Auth {

    private final LoginUseCase loginUseCase;

    public Auth(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest) {

        // Crear y devolver la respuesta
        AuthenticationResponse response =loginUseCase.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/saludo/spring")
    public String preuba() {



        return "Hola spring";
    }

}
