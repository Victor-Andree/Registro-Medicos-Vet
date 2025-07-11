package webvet.v1.application.useCase;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.PacienteVetDto;
import webvet.v1.domain.ports.input.MascotaVetIn;
import webvet.v1.domain.ports.output.MascotaVetOut;

import java.util.List;

@Service
public class MascotaVetUseCase implements MascotaVetIn {

    private final MascotaVetOut mascotaVetOut;


    public MascotaVetUseCase(MascotaVetOut mascotaVetOut) {
        this.mascotaVetOut = mascotaVetOut;
    }

    @Override
    public List<PacienteVetDto> listarPacientesAsignadosAlVeterinario(Long veterinarioId) {
        return mascotaVetOut.obtenerPacientesPorVeterinarioId(veterinarioId);
    }


}
