package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.MascotaEntity;

import java.util.Optional;

public interface MascotaRepository extends JpaRepository<MascotaEntity, Long> {

    Optional<MascotaEntity> findByNombre(String nombre);

}
