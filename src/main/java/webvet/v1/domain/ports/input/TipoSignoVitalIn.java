package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.TipoSignoVitalDto;

import java.util.List;
import java.util.Optional;

public interface TipoSignoVitalIn {

    Optional<TipoSignoVitalDto> createTipoSignoVital(TipoSignoVitalDto tipoSignoVitalDto);
    List<TipoSignoVitalDto> getAllTiposSignoVitales();


}
