package webvet.v1.domain.ports.output;

import webvet.v1.application.dto.MascotaDto;
import webvet.v1.domain.aggregates.model.Mascota;
import webvet.v1.infraestructure.entity.MascotaEntity;

import java.util.List;
import java.util.Optional;

public interface MascotaOut {

    Optional<Mascota> createMascota(Mascota mascota);

    List<Mascota> listarMascotas();

    Optional<MascotaEntity> findBynme (String nombre);

    Optional<Mascota> findByIdMascota (Long id);

    Optional<Mascota> updateMascota(Mascota mascota);

    List<Mascota> findMascotasByClienteId (Long clienteId);


}
