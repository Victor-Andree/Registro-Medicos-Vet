package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.RazaDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.RazaIn;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/")
@Tag(name = "Razas", description = "API para creacion de Razas")
public class RazaController {

    private final RazaIn razaIn;

    public RazaController(RazaIn razaIn) {
        this.razaIn = razaIn;
    }

    @PostMapping("/crearRaza")
    public ResponseEntity<ResponseBase<RazaDto>> createRaza(@RequestBody @Valid RazaDto razaDto){
        try {
            Optional<RazaDto> razaCreada = razaIn.createRaza(razaDto);

            if (razaCreada.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Raza creada correctamente", razaCreada.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear la raza", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error al crear la raza", null));
        }
    }

    @GetMapping("/listarRazas")
    public ResponseEntity<ResponseBase<List<RazaDto>>>geAllRazas(){
        List<RazaDto> razas = razaIn.getAllRazas();

        if (razas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen razas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Las razas se obtuvieron", razas));


    }

    @DeleteMapping("/eliminarRaza/{id}")
    public ResponseEntity<ResponseBase<Object>> eliminarRaza(@PathVariable Long id) {
        boolean eliminado = razaIn.deleteRaza(id);

        if (eliminado) {
            return ResponseEntity.ok(new ResponseBase<>(200, "Raza eliminada con éxito", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Raza no encontrada", null));
        }
    }

}
