package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Especie;

import java.util.List;
import java.util.Optional;

public interface EspecieOut {

    Optional<Especie> createEspecie (Especie especie);

    List<Especie> getAllEspecies ();

    boolean deleteEspecie(Long especieId);



}
