package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.model.Usuario;

import java.util.Optional;

public interface UsuarioIn {

    Optional<UsuarioDto> CrearUsuario(UsuarioDto usuario);
    Optional<UsuarioDto> findUsuarioByUsername(String username);


}
