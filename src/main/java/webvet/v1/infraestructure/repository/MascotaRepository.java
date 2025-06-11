package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.MascotaEntity;

public interface MascotaRepository extends JpaRepository<MascotaEntity, Long> {
}
