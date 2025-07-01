package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.SignoVitalDto;

import java.util.List;
import java.util.Optional;

public interface SignoVitalIn {

    Optional<SignoVitalDto> createSignoVital(SignoVitalDto signoVitalDto);
    List<SignoVitalDto> getAllSignoVitales();


}
