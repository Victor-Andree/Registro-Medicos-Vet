package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Visita;

import java.util.List;
import java.util.Optional;

public interface VisitaOut {

    Optional<Visita> createVisita(Visita visita);
    List<Visita> getAllVisitas();


}
