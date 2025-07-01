package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.TipoVisitaDto;

import java.util.List;
import java.util.Optional;

public interface TipoVisitaIn {

    Optional<TipoVisitaDto> createTipoVisita(TipoVisitaDto tipoVisitaDto);
    List<TipoVisitaDto> getAllTipoVisitas();

}
