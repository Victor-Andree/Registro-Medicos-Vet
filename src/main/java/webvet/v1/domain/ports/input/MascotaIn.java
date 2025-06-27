package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.MascotaDto;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.model.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaIn {

    Optional<MascotaDto> createMascota(MascotaDto mascotaDto);

    List<MascotaDto> getAllmascotas();

    Optional<Mascota> findMascotabyid(Long id);

    Optional<MascotaDto> findMascotabyname (String nombre);

    Optional<MascotaDto> updateMascota(MascotaDto mascotaDto);

    List<MascotaDto> findMascotaByCliente(Long clienteId);

    Optional<MascotaDto> ChangestatusMascota(Long mascotaId);






}
