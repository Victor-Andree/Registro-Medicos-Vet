package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.ClienteEnitity;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEnitity, Long> {

    Optional<ClienteEnitity> findByNombre(String nombre);

}
