package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.TipoServicio;

import java.util.List;
import java.util.Optional;

public interface TipoServicioOut {

    Optional<TipoServicio> createTiposServicio(TipoServicio tipoServicio);
    List<TipoServicio> getAllTiposServicios();


}
