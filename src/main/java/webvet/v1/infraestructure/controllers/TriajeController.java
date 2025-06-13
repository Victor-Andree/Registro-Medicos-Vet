package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.TriajeDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.TriajeIn;
import webvet.v1.infraestructure.mapper.TriajeMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/asistente/")
@Tag(name = "Triaje", description = "API para creacion de Triaje")
public class TriajeController {

    private final TriajeIn triajeIn;

    private final TriajeMapper triajeMapper;


    public TriajeController(TriajeIn triajeIn, TriajeMapper triajeMapper) {
        this.triajeIn = triajeIn;
        this.triajeMapper = triajeMapper;
    }

    @PostMapping("/crearTriaje")
    public ResponseEntity<ResponseBase<TriajeDto>> createTriaje(@RequestBody @Valid TriajeDto triajeDto){
        try {
            Optional<TriajeDto> triajeCreado = triajeIn.createTriaje(triajeDto);

            if (triajeCreado.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Triaje creada correctamente", triajeCreado.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear el triaje", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error al crear el triaje", null));
        }
    }

    @PutMapping("/triaje/{id}")
    public ResponseEntity<ResponseBase<TriajeDto>> updateTriaje(@RequestBody TriajeDto triajeDto) {
        if (triajeDto.getTriajeId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseBase<>(400, "Debe proporcionar el ID del triaje para actualizar los datos", null));
        }

        return triajeIn.updateTriaje(triajeDto)
                .map(triajeActualizado -> new ResponseEntity<>(
                        new ResponseBase<>(200, "Triaje actualizada correctamente", triajeActualizado),
                        HttpStatus.OK
                ))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "No se encontró el Triaje con el ID proporcionado", null)));
    }

    @GetMapping("/triaje/mascota/{mascotaId}")
    public ResponseEntity<ResponseBase<List<TriajeDto>>> findMascotaByCliente(@PathVariable Long mascotaId) {
        List<TriajeDto> triajes = triajeIn.findTriajeByMascota(mascotaId);

        if (triajes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "La mascita no tiene triaje registrado", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Triaje de la mascota obtenidas correctamente", triajes));
    }
}
