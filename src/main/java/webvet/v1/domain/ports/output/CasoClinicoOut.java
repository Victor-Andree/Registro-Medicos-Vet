package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.CasoClinico;

import java.util.List;
import java.util.Optional;

public interface CasoClinicoOut {

    Optional<CasoClinico> createCasoClinico(CasoClinico casoClinico);
    List<CasoClinico> getAllCasoClinicos();
    Optional<CasoClinico> updateCasoClinico(CasoClinico casoClinico);
    List<CasoClinico> findByMascotaId(Long mascotaId);
    Optional<CasoClinico> findById(Long casoClinicoId);


}
