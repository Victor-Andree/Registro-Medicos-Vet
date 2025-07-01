package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.SignoVitalEntity;

import java.util.Optional;

public interface SignoVitalRepository extends JpaRepository<SignoVitalEntity, Long> {

    Optional<SignoVitalEntity> findById(Long signoVitalId);

}
