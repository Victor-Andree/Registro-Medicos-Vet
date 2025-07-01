package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.SignoVitalDto;
import webvet.v1.application.dto.TipoSignoVitalDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.TipoSignoVitalIn;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vet/")
@Tag(name = "Tipos Signos Vitales " , description = "API para la creacion de signos vitales")
public class TipoSignoVitalController {

    private final TipoSignoVitalIn tipoSignoVitalIn;


    public TipoSignoVitalController(TipoSignoVitalIn tipoSignoVitalIn) {
        this.tipoSignoVitalIn = tipoSignoVitalIn;
    }

    @PostMapping("/crearTipoSignoVital")
    public ResponseEntity<ResponseBase<TipoSignoVitalDto>> crearTipoSignoVital(@RequestBody @Valid TipoSignoVitalDto tipoSignoVitalDto) {
        try {
            Optional<TipoSignoVitalDto> TiposignoVitalcreado = tipoSignoVitalIn.createTipoSignoVital(tipoSignoVitalDto);

            if (TiposignoVitalcreado.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "TipoSignoVital creado correctamente", TiposignoVitalcreado.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear el TipoSignoVital", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error en la creacion", null));
        }
    }

    @GetMapping("/listarTiposSignosVitales")
    public ResponseEntity<ResponseBase<List<TipoSignoVitalDto>>>getAllTiposSignosVitales(){
        List<TipoSignoVitalDto> allSignoVitales = tipoSignoVitalIn.getAllTiposSignoVitales();

        if (allSignoVitales.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen TiposSignoVital registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los TiposSignoVital se obtuvieron", allSignoVitales));


    }
}
