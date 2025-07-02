package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.TipoServicioDto;
import webvet.v1.application.dto.TipoVisitaDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.aggregates.model.TipoVisita;
import webvet.v1.domain.ports.input.TipoServicioIn;
import webvet.v1.domain.ports.input.TipoVisitaIn;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vet/")
@Tag(name = "Tipo Visitas", description = "API para el tipo de visitas")
public class TipoVisitaController {

    private final TipoVisitaIn tipoVisitaIn;

    public TipoVisitaController(TipoVisitaIn tipoVisitaIn) {
        this.tipoVisitaIn = tipoVisitaIn;
    }


    @PostMapping("/CreatTipoVisita")
    public ResponseEntity<ResponseBase<TipoVisitaDto>> crearTipoVisita(@RequestBody @Valid TipoVisitaDto tipoVisitaDto) {
        try {
            Optional<TipoVisitaDto> TipoVisitaCreado = tipoVisitaIn.createTipoVisita(tipoVisitaDto);

            if (TipoVisitaCreado.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "TipoVisita creado correctamente", TipoVisitaCreado.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear el TipoVisita", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error en la creacion", null));
        }
    }

    @GetMapping("/listarTiposVisitas")
    public ResponseEntity<ResponseBase<List<TipoVisitaDto>>>getAllTiposSVisitas(){
        List<TipoVisitaDto> allTiposVisitas = tipoVisitaIn.getAllTipoVisitas();

        if (allTiposVisitas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen TipoVisita registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los TipoVisita se obtuvieron", allTiposVisitas));


    }
}
