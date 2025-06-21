package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.ports.input.UsuarioIn;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Optional<UsuarioDto> usuarioRegistrado = usuarioIn.CrearUsuario(usuarioDto);

        if (usuarioRegistrado.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRegistrado.get());
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Ya existe un usuario con esos datos.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }



    @GetMapping("/usuario/{username}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioPorUsername(@PathVariable String username) {
        Optional<UsuarioDto> usuarioDto = usuarioIn.findUsuarioByUsername(username);

        if (usuarioDto.isPresent()) {
            return ResponseEntity.ok(usuarioDto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }






}
