package webvet.v1.domain.ports.output;

import webvet.v1.application.dto.ConsultaMedicaDto;

import java.util.List;

public interface ConsultaMedicaOut {

    List<ConsultaMedicaDto> listarConsultasResumen();


}
