package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.PerfilPersonalDto;

import java.util.List;
import java.util.Optional;

public interface PerfilPersonalIn {


    Optional<PerfilPersonalDto> createPerfil(PerfilPersonalDto perfilDto);

    Optional<PerfilPersonalDto> updatePerfil(PerfilPersonalDto perfilDto);

    Optional<PerfilPersonalDto> getPerfilByUsuarioId(int usuarioId);

    List<PerfilPersonalDto> getAllUsuarios();

}
