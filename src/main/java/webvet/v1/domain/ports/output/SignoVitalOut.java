package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.SignoVital;

import java.util.List;
import java.util.Optional;

public interface SignoVitalOut {

    Optional<SignoVital> createSignoVital(SignoVital signoVital);
    List<SignoVital> getAllSignoVitales();
}
