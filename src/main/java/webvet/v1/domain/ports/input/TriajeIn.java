package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.TriajeDto;
import webvet.v1.domain.aggregates.model.Triaje;

import java.util.List;
import java.util.Optional;

public interface TriajeIn {

    Optional<TriajeDto> createTriaje (TriajeDto triajeDto);

    List<TriajeDto> findTriajeByMascota(Long mascotaId);

    Optional<TriajeDto> updateTriaje (TriajeDto triajeDto);


}
