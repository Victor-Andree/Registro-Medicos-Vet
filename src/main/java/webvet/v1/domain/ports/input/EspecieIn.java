package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.EspecieDto;

import java.util.List;
import java.util.Optional;

public interface EspecieIn {

    Optional<EspecieDto> createEspecie (EspecieDto especieDto);

    List<EspecieDto> getAllEspecies();

    boolean deleteEspecie(Long especieId);



}
