package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.CasoClinicoDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.CasoClinicoIn;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vet")
@Tag(name = "Caso Clinicos" , description = "API para la creacion de casos clinicos")
public class CasoClinicoController {

    private final CasoClinicoIn casoClinicoIn;

    public CasoClinicoController(CasoClinicoIn casoClinicoIn) {
        this.casoClinicoIn = casoClinicoIn;
    }


    @PostMapping("/RegistrarCasoClinico")
    public ResponseEntity<ResponseBase<CasoClinicoDto>> createCasoClinico(@RequestBody CasoClinicoDto casoClinicoDto) {
        Optional<CasoClinicoDto> casoClinicoCreated = casoClinicoIn.createCasoClinico(casoClinicoDto);
        return casoClinicoCreated.map( casoClinico->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ResponseBase<>(201, "Caso Clinico creada correctamente", casoClinico)))
                .orElseGet(()->
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ResponseBase<>(500, "No se pudo registrar el Caso Clinico", null)));
    }

    @GetMapping("/listarCasosClinicos")
    public ResponseEntity<ResponseBase<List<CasoClinicoDto>>> getAllCasos() {
        List<CasoClinicoDto> casos = casoClinicoIn.getAllCasoClinicos();
        return ResponseEntity.ok(new ResponseBase<>(200, "Listado de Casos Clinicos", casos));
    }

    @PutMapping("/actualizarCaso")
    public ResponseEntity<ResponseBase<CasoClinicoDto>> updateCasoClinico(@RequestBody CasoClinicoDto casoClinicoDto) {
        Optional<CasoClinicoDto> updated = casoClinicoIn.updateCasoClinico(casoClinicoDto);
        return updated.map(casoClinico ->
                        ResponseEntity.ok(new ResponseBase<>(200, "Caso Clinico actualizada", casoClinico)))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseBase<>(404, "No se pudo actualizar el CasoClinico", null)));
    }

    @GetMapping("/CasoClinico/mascota/{mascotaId}")
    public ResponseEntity<ResponseBase<List<CasoClinicoDto>>> obtenerCasoClinicoPorMascota(@PathVariable Long mascotaId) {
        List<CasoClinicoDto> casosClinicos = casoClinicoIn.findByMascotaId(mascotaId);

        if (casosClinicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "La mascota no tiene casos clinicos registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Caso Clinico de la mascota obtenidas correctamente", casosClinicos));
    }

}
