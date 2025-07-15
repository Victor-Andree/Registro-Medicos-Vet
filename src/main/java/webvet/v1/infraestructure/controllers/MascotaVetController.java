package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webvet.v1.application.dto.CitaDto;
import webvet.v1.application.dto.ConsultaMedicaDto;
import webvet.v1.application.dto.MascotaDto;
import webvet.v1.application.dto.PacienteVetDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.CitaIn;
import webvet.v1.domain.ports.input.ConsultaMedicaIn;
import webvet.v1.domain.ports.input.MascotaIn;
import webvet.v1.domain.ports.input.MascotaVetIn;
import webvet.v1.infraestructure.mapper.CitaMapper;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vet/")
@Tag(name = "MascotaVet", description = "API para la visualizacion de Mascotas")
public class MascotaVetController {

    private final MascotaIn mascotaIn;

    private final MascotaVetIn mascotaVetIn;

    private final ConsultaMedicaIn consultaMedicaIn;

    private final CitaIn citaIn;

    private final CitaMapper citaMapper;



    public MascotaVetController(MascotaIn mascotaIn, MascotaVetIn mascotaVetIn, ConsultaMedicaIn consultaMedicaIn, CitaIn citaIn, CitaMapper citaMapper) {
        this.mascotaIn = mascotaIn;
        this.mascotaVetIn = mascotaVetIn;
        this.consultaMedicaIn = consultaMedicaIn;
        this.citaIn = citaIn;
        this.citaMapper = citaMapper;
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase<List<MascotaDto>>> listarMascotas() {
        List<MascotaDto> mascotas = mascotaIn.getAllmascotas();
        if (mascotas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, "No hay mascotas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Mascotas obtenidas correctamente", mascotas));
    }

    @GetMapping("/pacientes/{veterinarioId}")
    public ResponseEntity<ResponseBase<List<PacienteVetDto>>> listarPacientesPorVeterinario(@PathVariable Long veterinarioId) {
        List<PacienteVetDto> pacientes = mascotaVetIn.listarPacientesAsignadosAlVeterinario(veterinarioId);

        if (pacientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, "No hay pacientes asignados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Pacientes asignados encontrados", pacientes));
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResponseBase<List<ConsultaMedicaDto>>> obtenerResumen() {
        var data = consultaMedicaIn.listarConsultasResumen();
        return ResponseEntity.ok(new ResponseBase<>(200, "Consultas médicas encontradas", data));
    }

    @GetMapping("/listarCitas")
    public ResponseEntity<ResponseBase<List<CitaDto>>> getAllCitas() {
        List<CitaDto> citas = citaIn.getAllCitas();
        return ResponseEntity.ok(new ResponseBase<>(200, "Listado de citas", citas));
    }

    @GetMapping("/Listarcita/{id}")
    public ResponseEntity<ResponseBase<CitaDto>> obtenerCita(@PathVariable Long id) {
        return citaIn.foundCitaById(id)
                .map(cita -> citaMapper.toCitaDto(cita))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "Cita encontrada", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Cita no encontrado", null)));
    }

    @GetMapping("/Listarcitas/hoy")
    public ResponseEntity<ResponseBase<List<CitaDto>>> getAllCitasByToday() {
        List<CitaDto> citasListar = citaIn.getAllCitasByToday();
        return ResponseEntity.ok(new ResponseBase<>(200, "Listado de citas", citasListar));
    }
}
