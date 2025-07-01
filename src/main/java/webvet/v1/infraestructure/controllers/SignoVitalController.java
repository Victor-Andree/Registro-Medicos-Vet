package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.SignoVitalDto;
import webvet.v1.application.dto.VisitaDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.SignoVitalIn;
import webvet.v1.domain.ports.output.SignoVitalOut;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vet/")
@Tag(name = "SignosVitales", description = "API para signos Vitales")
public class SignoVitalController {

    private final SignoVitalIn signoVitalIn;


    public SignoVitalController(SignoVitalIn signoVitalIn) {
        this.signoVitalIn = signoVitalIn;
    }

    @PostMapping("/crearSignoVital")
    public ResponseEntity<ResponseBase<SignoVitalDto>> crearSignoVital(@RequestBody @Valid SignoVitalDto signoVitalDto) {
        try {
            Optional<SignoVitalDto> signoVitalcreado = signoVitalIn.createSignoVital(signoVitalDto);

            if (signoVitalcreado.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "SignoVital creado correctamente", signoVitalcreado.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear el SignoVital", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error en la creacion", null));
        }
    }

    @GetMapping("/listarSignosVitales")
    public ResponseEntity<ResponseBase<List<SignoVitalDto>>>getAllSignosVitales(){
        List<SignoVitalDto> signoVitalDtos = signoVitalIn.getAllSignoVitales();

        if (signoVitalDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen SignoVital registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los SignoVitales se obtuvieron", signoVitalDtos));


    }

}
