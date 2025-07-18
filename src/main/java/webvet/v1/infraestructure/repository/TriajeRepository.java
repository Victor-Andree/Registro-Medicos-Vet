package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.MascotaEntity;
import webvet.v1.infraestructure.entity.TriajeEntity;

import java.util.List;
import java.util.Optional;

public interface TriajeRepository extends JpaRepository<TriajeEntity, Long> {

    List<TriajeEntity> findByMascota_MascotaId(Long id);

    Optional<TriajeEntity> findTopByMascota_MascotaIdOrderByFechaRegistroDesc(Long mascotaId);


}
