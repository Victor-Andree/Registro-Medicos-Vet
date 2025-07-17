package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.EspecieDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.EspecieIn;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/")
@Tag(name = "Especies", description = "API para creacion de Especies")
public class EspecieController {

    private final EspecieIn especieIn;

    public EspecieController(EspecieIn especieIn) {
        this.especieIn = especieIn;
    }

    @PostMapping("/crearEspecie")
    public ResponseEntity<ResponseBase<EspecieDto>> createEspecie(@RequestBody @Valid EspecieDto especieDto){
        try {
            Optional<EspecieDto> especieCreada = especieIn.createEspecie(especieDto);

            if (especieCreada.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Especie creada correctamente", especieCreada.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear la especie", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error al crear la especie", null));
        }
    }

    @GetMapping("/listarEspecies")
    public ResponseEntity<ResponseBase<List<EspecieDto>>>getAllespecies(){
        List<EspecieDto> especies = especieIn.getAllEspecies();

        if (especies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen especies registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Las especies se obtuvieron", especies));


    }

    @DeleteMapping("/eliminarEspecie/{id}")
    public ResponseEntity<ResponseBase<Object>> eliminarEspecie(@PathVariable Long id) {
        boolean eliminado = especieIn.deleteEspecie(id);

        if (eliminado) {
            return ResponseEntity.ok(new ResponseBase<>(200, "Especie eliminada con éxito", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Especie no encontrada", null));
        }
    }




}
