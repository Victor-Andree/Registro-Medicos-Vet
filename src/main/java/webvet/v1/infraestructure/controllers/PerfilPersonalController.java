package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.PerfilPersonalDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.PerfilPersonalIn;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "Perfil Personal", description = "API para gestión del perfil personal de usuarios")
public class PerfilPersonalController {

    private final PerfilPersonalIn perfilPersonalIn;


    public PerfilPersonalController(PerfilPersonalIn perfilPersonalIn) {
        this.perfilPersonalIn = perfilPersonalIn;
    }

    @PostMapping("/crearPerfil")
    public ResponseEntity<ResponseBase<PerfilPersonalDto>> crearPerfil(@RequestBody PerfilPersonalDto dto) {
        Optional<PerfilPersonalDto> creado = perfilPersonalIn.createPerfil(dto);
        if (creado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseBase<>(400, "No se pudo crear el perfil", null));
        }
        return ResponseEntity.ok(new ResponseBase<>(200, "Perfil creado correctamente", creado.get()));
    }

    @PutMapping("/editarPerfil/{perfilUsuarioId}")
    public ResponseEntity<ResponseBase<PerfilPersonalDto>> actualizarPerfil(@RequestBody PerfilPersonalDto dto) {
        Optional<PerfilPersonalDto> actualizado = perfilPersonalIn.updatePerfil(dto);
        if (actualizado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Perfil no encontrado para actualizar", null));
        }
        return ResponseEntity.ok(new ResponseBase<>(200, "Perfil actualizado correctamente", actualizado.get()));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ResponseBase<PerfilPersonalDto>> obtenerPerfilPorUsuario(@PathVariable int usuarioId) {
        Optional<PerfilPersonalDto> perfil = perfilPersonalIn.getPerfilByUsuarioId(usuarioId);
        if (perfil.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Perfil no encontrado", null));
        }
        return ResponseEntity.ok(new ResponseBase<>(200, "Perfil encontrado", perfil.get()));
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase<List<PerfilPersonalDto>>> listarPerfiles() {
        List<PerfilPersonalDto> perfiles = perfilPersonalIn.getAllUsuarios();
        if (perfiles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, "No hay perfiles registrados", perfiles));
        }
        return ResponseEntity.ok(new ResponseBase<>(200, "Lista de perfiles obtenida", perfiles));
    }
}
