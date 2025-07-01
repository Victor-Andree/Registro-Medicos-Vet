package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.TipoVisitaEntity;

public interface TipoVisitaRepository extends JpaRepository<TipoVisitaEntity, Long> {
}
