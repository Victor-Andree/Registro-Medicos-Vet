package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.PerfilPersonalEntity;

import java.util.Optional;

public interface PerfilPersonalRepository extends JpaRepository<PerfilPersonalEntity, Long> {

    Optional<PerfilPersonalEntity> findByUsuario_Id(int usuarioId);


}
