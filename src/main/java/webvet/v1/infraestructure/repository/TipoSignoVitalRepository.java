package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.SignoVitalEntity;
import webvet.v1.infraestructure.entity.TipoSignoVitalEntity;

import java.util.Optional;

public interface TipoSignoVitalRepository extends JpaRepository<TipoSignoVitalEntity, Long> {

    Optional<TipoSignoVitalEntity> findById(Long tipoSignoVitalId);


}
