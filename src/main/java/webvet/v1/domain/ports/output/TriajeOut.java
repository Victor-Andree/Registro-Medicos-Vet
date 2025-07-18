package webvet.v1.domain.ports.output;

import webvet.v1.application.dto.TriajeDto;
import webvet.v1.domain.aggregates.model.Triaje;

import java.util.List;
import java.util.Optional;

public interface TriajeOut {

    Optional<Triaje> createTriaje (Triaje triaje);

    List<Triaje> findTriajeByMascota (Long mascotaId);

    Optional<Triaje> updateTriaje (Triaje triaje);

    Optional<Triaje> updateTriajeByMascotaId(Long mascotaId);


}
