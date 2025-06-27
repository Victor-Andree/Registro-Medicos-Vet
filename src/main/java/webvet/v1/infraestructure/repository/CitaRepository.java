package webvet.v1.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webvet.v1.infraestructure.entity.CitaEntity;
import webvet.v1.infraestructure.entity.MascotaEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaRepository extends JpaRepository<CitaEntity, Long> {

    List<CitaEntity> findByFechaRegistroBetween(LocalDateTime start, LocalDateTime end);

    List<CitaEntity> findByCliente_ClienteId(Long clienteId);



}
