package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.VisitaDto;
import webvet.v1.domain.aggregates.model.Visita;

import java.util.List;
import java.util.Optional;

public interface VisitaIn {


    Optional<VisitaDto> createVisita(VisitaDto visitaDto);
    List<VisitaDto> getAllVisitas();


}
