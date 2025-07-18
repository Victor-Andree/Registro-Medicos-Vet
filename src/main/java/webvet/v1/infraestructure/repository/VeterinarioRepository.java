package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.VeterinarioEntity;

import java.util.Optional;

public interface VeterinarioRepository extends JpaRepository<VeterinarioEntity, Long> {

    Optional<VeterinarioEntity> findByApellido(String apellido);

    Optional<VeterinarioEntity> findByDni(String dni);



}
