package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.UsuarioIn;

import java.util.HashMap;
import java.util.List;
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

    @PutMapping("/actualizar")
    public ResponseEntity<ResponseBase<UsuarioDto>> actualizarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Optional<UsuarioDto> usuarioActualizado = usuarioIn.actualizarUsuario(usuarioDto);

        if (usuarioActualizado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Usuario no encontrado", null));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Usuario actualizado correctamente", usuarioActualizado.get()));
    }

    @PutMapping("/desactivar/{usuarioId}")
    public ResponseEntity<ResponseBase<UsuarioDto>> desactivarUsuario(@PathVariable int usuarioId) {
        Optional<UsuarioDto> usuarioDesactivado = usuarioIn.desactivarUsuario(usuarioId);

        if (usuarioDesactivado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Usuario no encontrado", null));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Usuario desactivado correctamente", usuarioDesactivado.get()));
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase<List<UsuarioDto>>> listarUsuarios() {
        List<UsuarioDto> usuarios = usuarioIn.listarUsuarios();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, "No hay usuarios registrados", usuarios));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Usuarios obtenidos correctamente", usuarios));
    }






}
