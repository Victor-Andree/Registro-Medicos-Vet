package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webvet.v1.application.dto.CasoClinicoDto;
import webvet.v1.application.dto.TipoServicioDto;
import webvet.v1.application.dto.VisitaDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.CasoClinicoIn;
import webvet.v1.domain.ports.input.TipoServicioIn;
import webvet.v1.domain.ports.input.VisitaIn;
import webvet.v1.infraestructure.mapper.CasoClinicoMapper;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/asistente")
@Tag(name = "Casos Clinicos Asistente" , description = "API para el asistente ")
public class AsistenteCasoTipoController {

    private final VisitaIn visitaIn;
    private final CasoClinicoIn casoClinicoIn;
    private final TipoServicioIn tipoServicioIn;
    private final CasoClinicoMapper casoClinicoMapper;


    public AsistenteCasoTipoController(VisitaIn visitaIn, CasoClinicoIn casoClinicoIn, TipoServicioIn tipoServicioIn, CasoClinicoMapper casoClinicoMapper) {
        this.visitaIn = visitaIn;
        this.casoClinicoIn = casoClinicoIn;
        this.tipoServicioIn = tipoServicioIn;
        this.casoClinicoMapper = casoClinicoMapper;
    }

    @GetMapping("/listarCasos")
    public ResponseEntity<ResponseBase<List<CasoClinicoDto>>> getAllCasos() {
        List<CasoClinicoDto> casos = casoClinicoIn.getAllCasoClinicos();
        return ResponseEntity.ok(new ResponseBase<>(200, "Listado de Casos Clinicos", casos));
    }

    @GetMapping("/CasosClinicos/mascota/{mascotaId}")
    public ResponseEntity<ResponseBase<List<CasoClinicoDto>>> obtenerCasoClinicoPorMascota(@PathVariable Long mascotaId) {
        List<CasoClinicoDto> casosClinicos = casoClinicoIn.findByMascotaId(mascotaId);

        if (casosClinicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "La mascota no tiene casos clinicos registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Caso Clinico de la mascota obtenidas correctamente", casosClinicos));
    }

    @GetMapping("/listarCasoClicnicos/{casoClinidoId}")
    public ResponseEntity<ResponseBase<CasoClinicoDto>> getCasoClinico(@PathVariable Long casoClinidoId){
        return casoClinicoIn.findById(casoClinidoId)
                .map(casoClinico -> casoClinicoMapper.toCasoClinicoDto(casoClinico))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "Caso Clinico encontrado", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Caso Clinico no encontrado", null)));

    }

    @GetMapping("/ListarServicios")
    public ResponseEntity<ResponseBase<List<TipoServicioDto>>>getAllTiposServicios(){
        List<TipoServicioDto> allTipoServicios = tipoServicioIn.getAllTiposServicios();

        if (allTipoServicios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen tipoServicioS registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los tipoServicioS se obtuvieron", allTipoServicios));


    }

    @GetMapping("/listarTodasLasVisitas")
    public ResponseEntity<ResponseBase<List<VisitaDto>>>getAllVisitas(){
        List<VisitaDto> visitaDtos = visitaIn.getAllVisitas();

        if (visitaDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen Visitas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Las Visitas se obtuvieron", visitaDtos));


    }


}
