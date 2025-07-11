package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.ConsultaMedicaDto;

import java.util.List;

public interface ConsultaMedicaIn {

    List<ConsultaMedicaDto> listarConsultasResumen();


}
