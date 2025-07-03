package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.CitaDto;
import webvet.v1.application.dto.TipoServicioDto;
import webvet.v1.application.dto.VeterinarioDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.CitaIn;
import webvet.v1.domain.ports.input.TipoServicioIn;
import webvet.v1.domain.ports.input.VeterinarioIn;
import webvet.v1.infraestructure.mapper.CitaMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/asistente/")
@Tag(name = "Citas", description = "API para creacion de Citas")
public class CitaController {

    private final CitaIn citaIn;
    private final TipoServicioIn tipoServicioIn;
    private final VeterinarioIn veterinarioIn;
    private final CitaMapper citaMapper;


    public CitaController(CitaIn citaIn, TipoServicioIn tipoServicioIn, VeterinarioIn veterinarioIn, CitaMapper citaMapper) {
        this.citaIn = citaIn;
        this.tipoServicioIn = tipoServicioIn;
        this.veterinarioIn = veterinarioIn;
        this.citaMapper = citaMapper;
    }

    @PostMapping("/RegistrarCita")
    public ResponseEntity<ResponseBase<CitaDto>>CreateCita(@RequestBody CitaDto citaDto) {
        Optional<CitaDto> citacreated = citaIn.createCita(citaDto);
        return citacreated.map( cita->
                ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Cita creada correctamente", cita)))
                .orElseGet(()->
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseBase<>(500, "No se pudo registrar la cita", null)));
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase<List<CitaDto>>> getAllCitas() {
        List<CitaDto> citas = citaIn.getAllCitas();
        return ResponseEntity.ok(new ResponseBase<>(200, "Listado de citas", citas));
    }

    @GetMapping("/Cita/cliente/{clienteId}")
    public ResponseEntity<ResponseBase<List<CitaDto>>> getCitaByCliente(@PathVariable Long clienteId) {
        List<CitaDto> citas = citaIn.getCitasByClienteId(clienteId);

        if (citas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Cita no encontrada", citas));

        }
        return ResponseEntity.ok(new ResponseBase<>(200, "Mascotas del cliente obtenidas correctamente", citas));

    }

    @GetMapping("/fecha")
    public ResponseEntity<ResponseBase<List<CitaDto>>> getCitasByFecha(@RequestParam String fecha) {
        try {
            LocalDate localDate = LocalDate.parse(fecha);
            List<CitaDto> citas = citaIn.getCitaByFecha(localDate);
            return ResponseEntity.ok(new ResponseBase<>(200, "Citas en la fecha especificada", citas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseBase<>(400, "Fecha inválida", Collections.emptyList()));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ResponseBase<CitaDto>> updateCita(@RequestBody CitaDto citaDto) {
        Optional<CitaDto> updated = citaIn.updateCita(citaDto);
        return updated.map(cita ->
                        ResponseEntity.ok(new ResponseBase<>(200, "Cita actualizada", cita)))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseBase<>(404, "No se pudo actualizar la cita", null)));
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

    @GetMapping("/listarVeterinarios")
    public ResponseEntity<ResponseBase<List<VeterinarioDto>>>getAllVeterinarios(){
        List<VeterinarioDto> veterinarios = veterinarioIn.getAllVeterinarios();

        if (veterinarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen Veterinarios registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los Veterinarios se obtuvieron", veterinarios));


    }

    @GetMapping("/cita/{id}")
    public ResponseEntity<ResponseBase<CitaDto>> obtenerCita(@PathVariable Long id) {
        return citaIn.foundCitaById(id)
                .map(cita -> citaMapper.toCitaDto(cita))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "Cita encontrada", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Cita no encontrado", null)));
    }


}
