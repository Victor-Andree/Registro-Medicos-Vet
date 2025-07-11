package webvet.v1.domain.ports.output;

import webvet.v1.application.dto.PacienteVetDto;

import java.util.List;

public interface MascotaVetOut {

    List<PacienteVetDto> obtenerPacientesPorVeterinarioId(Long veterinarioId);


}
