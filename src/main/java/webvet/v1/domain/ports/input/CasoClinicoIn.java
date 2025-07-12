package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.CasoClinicoDto;
import webvet.v1.domain.aggregates.model.CasoClinico;

import java.util.List;
import java.util.Optional;

public interface CasoClinicoIn {

    Optional<CasoClinicoDto> createCasoClinico(CasoClinicoDto casoClinicoDto);
    List<CasoClinicoDto> getAllCasoClinicos();
    Optional<CasoClinicoDto> updateCasoClinico(CasoClinicoDto casoClinicoDto);
    List<CasoClinicoDto> findByMascotaId(Long mascotaId);
    Optional<CasoClinico> findById(Long casoClinicoId);


}
