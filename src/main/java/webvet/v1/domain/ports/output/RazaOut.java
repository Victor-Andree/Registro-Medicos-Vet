package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Raza;

import java.util.List;
import java.util.Optional;

public interface RazaOut {

    Optional<Raza> createRaza(Raza raza);

    List<Raza> getAllRazas();


}
