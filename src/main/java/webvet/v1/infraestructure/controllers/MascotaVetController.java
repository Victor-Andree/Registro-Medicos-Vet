package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webvet.v1.application.dto.MascotaDto;
import webvet.v1.application.dto.PacienteVetDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.MascotaIn;
import webvet.v1.domain.ports.input.MascotaVetIn;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vet/")
@Tag(name = "MascotaVet", description = "API para la visualizacion de Mascotas")
public class MascotaVetController {

    private final MascotaIn mascotaIn;

    private final MascotaVetIn mascotaVetIn;


    public MascotaVetController(MascotaIn mascotaIn, MascotaVetIn mascotaVetIn) {
        this.mascotaIn = mascotaIn;
        this.mascotaVetIn = mascotaVetIn;
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
}
