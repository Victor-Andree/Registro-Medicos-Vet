package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.TipoServicioDto;
import java.util.List;
import java.util.Optional;

public interface TipoServicioIn {

    Optional<TipoServicioDto> createTiposServicio(TipoServicioDto tipoServicioDto);
    List<TipoServicioDto> getAllTiposServicios();


}
