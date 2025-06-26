package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.EspecieEntity;

import java.util.Optional;

public interface EspecieRepository extends JpaRepository<EspecieEntity, Long> {

    Optional<EspecieEntity> findById(Long especieId);

}
