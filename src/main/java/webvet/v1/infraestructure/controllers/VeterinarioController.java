package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.VeterinarioDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.VeterinarioIn;
import webvet.v1.infraestructure.mapper.VeterinarioMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/")
@Tag(name = "Veterinarios", description = "API para creacion de Veterinarios")
public class VeterinarioController {

    private final VeterinarioIn veterinarioIn;


    public VeterinarioController(VeterinarioIn veterinarioIn) {
        this.veterinarioIn = veterinarioIn;
    }

    @PostMapping("/crearVeterinario")
    public ResponseEntity<ResponseBase<VeterinarioDto>> createVeterinario(@RequestBody @Valid VeterinarioDto veterinarioDto) {
        try {
            Optional<VeterinarioDto> vetCreado = veterinarioIn.CreateVeterinario(veterinarioDto);

            if (vetCreado.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Veterinario creado correctamente", vetCreado.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear el Veterinario", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error al crear el Veterinario", null));
        }
    }

    @GetMapping("/listarVeterinarios")
    public ResponseEntity<ResponseBase<List<VeterinarioDto>>>getAllVeterinarios(){
        List<VeterinarioDto> veterinarios = veterinarioIn.getAllVeterinarios();

        if (veterinarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen Veterinarios registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los Veterinarios se obtuvieron", veterinarios));


    }

    @GetMapping("/veterinario//{apellido}")
    public ResponseEntity<ResponseBase<VeterinarioDto>> getVeterinarioByApellido(@PathVariable String apellido) {
        return veterinarioIn.getVeterinarioByApellido(apellido)
                .map(veterinarioDto -> ResponseEntity.ok(new ResponseBase<>(200, "Veterinario encontrado", veterinarioDto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Veterinario con apellido"+ apellido + "no encontrado", null )));
    }
}
