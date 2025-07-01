package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.CasoClinicoEntity;

import java.util.List;

public interface CasoClinicoRepository extends JpaRepository<CasoClinicoEntity, Long> {

    List<CasoClinicoEntity> findByMascota_MascotaId(Long mascotaId);
}
