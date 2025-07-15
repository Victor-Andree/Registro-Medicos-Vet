package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.CitaDto;
import webvet.v1.application.dto.VisitaDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.VisitaIn;
import webvet.v1.infraestructure.mapper.VisitaMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vet/")
@Tag(name = "visitas" , description = "API para creacion de las visitas")
public class VisitaController {

    private final VisitaIn visitaIn;

    private final VisitaMapper visitaMapper;


    public VisitaController(VisitaIn visitaIn, VisitaMapper visitaMapper) {
        this.visitaIn = visitaIn;
        this.visitaMapper = visitaMapper;
    }

    @PostMapping("/crearVisita")
    public ResponseEntity<ResponseBase<VisitaDto>> crearVisita(@RequestBody @Valid VisitaDto visitaDto) {
        try {
            Optional<VisitaDto> visitaCreada = visitaIn.createVisita(visitaDto);

            if (visitaCreada.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Visita creada correctamente", visitaCreada.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear la Visita", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error al crear la Visita", null));
        }
    }

    @GetMapping("/listarVisitass")
    public ResponseEntity<ResponseBase<List<VisitaDto>>>getAllVisitas(){
        List<VisitaDto> visitaDtos = visitaIn.getAllVisitas();

        if (visitaDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen Visitas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Las Visitas se obtuvieron", visitaDtos));


    }

    @GetMapping("/visita/{id}")
    public ResponseEntity<ResponseBase<VisitaDto>> obtenerVisita(@PathVariable Long visitaId) {
        return visitaIn.foundVisitaById(visitaId)
                .map(visita -> visitaMapper.toVisitaDto(visita))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "Visita encontrada", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Visita no encontrado", null)));
    }

    @GetMapping("/visita/tipoVisita/{tipoVisitaId}")
    public ResponseEntity<ResponseBase<List<VisitaDto>>> getVisitaByTipoVisitaId(@PathVariable Long tipoVisitaId) {
        List<VisitaDto> visitaDtos = visitaIn.foundVisitaByTipoVisitaId(tipoVisitaId);

        if (visitaDtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Visitas no encontrada", visitaDtos));

        }
        return ResponseEntity.ok(new ResponseBase<>(200, "Visitas con el tipo de visita obtenidas correctamente", visitaDtos));

    }


}
