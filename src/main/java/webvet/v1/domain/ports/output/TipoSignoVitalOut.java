package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.TipoSignoVital;

import java.util.List;
import java.util.Optional;

public interface TipoSignoVitalOut {

    Optional<TipoSignoVital> createTipoSignoVital(TipoSignoVital tipoSignoVital);
    List<TipoSignoVital> getAllTiposSignoVitales();

}
