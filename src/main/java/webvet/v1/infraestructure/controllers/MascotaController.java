package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.ClienteDto;
import webvet.v1.application.dto.MascotaDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.MascotaIn;
import webvet.v1.infraestructure.mapper.MascotaMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/asistente/")
@Tag(name = "Mascota", description = "API para creacion de Mascota")
public class MascotaController {

    private final MascotaIn mascotaIn;

    private  final MascotaMapper mascotaMapper;


    public MascotaController(MascotaIn mascotaIn, MascotaMapper mascotaMapper) {
        this.mascotaIn = mascotaIn;
        this.mascotaMapper = mascotaMapper;
    }


    @PostMapping("/crearMascota")
    public ResponseEntity<ResponseBase<MascotaDto>> createMascota(@RequestBody @Valid MascotaDto mascotaDto){
        try {
            Optional<MascotaDto> mascotaCreada = mascotaIn.createMascota(mascotaDto);

            if (mascotaCreada.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Mascota creada correctamente", mascotaCreada.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear la mascota", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error al crear la mascota", null));
        }
    }

    @GetMapping("/listarMascota")
    public ResponseEntity<ResponseBase<List<MascotaDto>>>obtenerMascotas(){
        List<MascotaDto> mascotas = mascotaIn.getAllmascotas();

        if (mascotas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen mascotas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los mascotas se obtuvieron", mascotas));


    }

    @GetMapping("/mascota/{id}")
    public ResponseEntity<ResponseBase<MascotaDto>> obternerMascota(@PathVariable Long id) {
        return mascotaIn.findMascotabyid(id)
                .map(mascota -> mascotaMapper.toMascotaDto(mascota))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "mascota encontrada", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "mascota no encontrada", null)));
    }

    @GetMapping("/mascota/nombre/{nombre}")
    public ResponseEntity<ResponseBase<MascotaDto>> obtenerMascotaPorNonre(@PathVariable String nombre) {
        return mascotaIn.findMascotabyname(nombre)
                .map(mascotaDto -> ResponseEntity.ok(new ResponseBase<>(200, "Mascota encontrado", mascotaDto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Mliente con nombre"+ nombre + "no encontrado", null )));
    }


}


