package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.RazaEntity;

import java.util.Optional;

public interface RazaRepository extends JpaRepository<RazaEntity, Long> {

    Optional<RazaEntity> findById(Long razaId);

}
