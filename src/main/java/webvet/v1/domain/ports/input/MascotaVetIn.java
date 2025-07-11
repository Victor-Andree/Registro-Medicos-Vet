package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.PacienteVetDto;

import java.util.List;

public interface MascotaVetIn {

    List<PacienteVetDto> listarPacientesAsignadosAlVeterinario(Long veterinarioId);


}
