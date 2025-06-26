package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.ClienteEntity;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByNombre(String nombre);

}
