package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.MascotaDto;
import webvet.v1.domain.aggregates.model.Mascota;

import java.util.Optional;

public interface MascotaIn {

    Optional<Mascota> crearMascota(MascotaDto mascotaDto);


}
