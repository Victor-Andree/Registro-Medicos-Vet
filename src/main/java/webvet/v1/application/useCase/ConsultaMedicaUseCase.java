package webvet.v1.application.useCase;


import org.springframework.stereotype.Service;
import webvet.v1.application.dto.ConsultaMedicaDto;
import webvet.v1.domain.ports.input.ConsultaMedicaIn;
import webvet.v1.domain.ports.output.CitaOut;

import java.util.List;

@Service
public class ConsultaMedicaUseCase implements ConsultaMedicaIn {

    private final CitaOut citaOut;

    public ConsultaMedicaUseCase( CitaOut citaOut) {
        this.citaOut = citaOut;
    }

    @Override
    public List<ConsultaMedicaDto> listarConsultasResumen() {
        return citaOut.getConsultasMedicas();
    }
}
