package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.TipoServicioEntity;
import webvet.v1.infraestructure.entity.TipoSignoVitalEntity;

import java.util.Optional;

public interface TipoServicioRepository extends JpaRepository<TipoServicioEntity, Long> {

    Optional<TipoServicioEntity> findById(Long tipoServicioId);


}
