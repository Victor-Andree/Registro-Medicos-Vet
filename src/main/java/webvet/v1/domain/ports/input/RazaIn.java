package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.RazaDto;

import java.util.List;
import java.util.Optional;

public interface RazaIn {

    Optional<RazaDto> createRaza (RazaDto raza);

    List<RazaDto> getAllRazas();

    boolean deleteRaza(Long razaId);

}
