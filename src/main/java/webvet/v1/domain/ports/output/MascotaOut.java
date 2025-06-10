package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaOut {

    Optional<Mascota> crearMascota(Mascota mascota);

    List<Mascota> listarMascotas();

    Optional<Mascota> EncontrarMascota(Mascota mascota);


}
