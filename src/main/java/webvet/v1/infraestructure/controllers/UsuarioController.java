package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.ports.input.UsuarioIn;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authentication/")
@Tag(name = "Usuarios", description = "API para creacion de Usuarios")
public class UsuarioController {

    private final UsuarioIn usuarioIn;

    public UsuarioController(UsuarioIn usuarioIn) {
        this.usuarioIn = usuarioIn;
    }


    @PostMapping("/register")
    public ResponseEntity<UsuarioDto> registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Optional<UsuarioDto> usuarioRegistrado = usuarioIn.CrearUsuario(usuarioDto);

        if (usuarioRegistrado.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRegistrado.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // sin body
        }
    }



}
