package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.RazaEntity;
import webvet.v1.infraestructure.entity.VisitaEntity;

import java.util.Optional;

public interface VisitaRepository extends JpaRepository<VisitaEntity, Long> {

    Optional<VisitaEntity> findById(Long visitaId);


}
