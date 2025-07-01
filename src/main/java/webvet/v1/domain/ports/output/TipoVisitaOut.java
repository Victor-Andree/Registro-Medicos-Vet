package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.TipoVisita;

import java.util.List;
import java.util.Optional;

public interface TipoVisitaOut {

    Optional<TipoVisita> createTipoVisita(TipoVisita tipoVisita);
    List<TipoVisita> getAllTipoVisitas();


}
