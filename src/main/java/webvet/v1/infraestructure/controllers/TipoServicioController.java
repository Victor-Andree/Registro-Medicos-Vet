package webvet.v1.infraestructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.TipoServicioDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.TipoServicioIn;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "TipoServicio" , description = "Api para la creacion de los tipo de servicios")
public class TipoServicioController {

    private final TipoServicioIn tipoServicioIn;


    public TipoServicioController(TipoServicioIn tipoServicioIn) {
        this.tipoServicioIn = tipoServicioIn;
    }

    @PostMapping("/crearTipoServicio")
    public ResponseEntity<ResponseBase<TipoServicioDto>> crearTipoServicios(@RequestBody @Valid TipoServicioDto tipoServicioDto) {
        try {
            Optional<TipoServicioDto> tipoServicioCreado = tipoServicioIn.createTiposServicio(tipoServicioDto);

            if (tipoServicioCreado.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "TipoServicio creado correctamente", tipoServicioCreado.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear el tipoServicio", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error en la creacion", null));
        }
    }

    @GetMapping("/listarTiposServicios")
    public ResponseEntity<ResponseBase<List<TipoServicioDto>>>getAllTiposServicios(){
        List<TipoServicioDto> allTipoServicios = tipoServicioIn.getAllTiposServicios();

        if (allTipoServicios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen tipoServicioS registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los tipoServicioS se obtuvieron", allTipoServicios));


    }


    @DeleteMapping("/eliminarTipoServicio/{id}")
    public ResponseEntity<ResponseBase<Object>> eliminarTipoServicio(@PathVariable Long id) {
        boolean eliminado = tipoServicioIn.deleteTipoServicio(id);

        if (eliminado) {
            return ResponseEntity.ok(new ResponseBase<>(200, "Tipo de Servicio eliminada con éxito", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Tipo de Servicio no encontrada", null));
        }
    }


}
