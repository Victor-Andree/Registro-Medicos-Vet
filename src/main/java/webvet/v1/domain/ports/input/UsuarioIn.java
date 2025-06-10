package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.UsuarioDto;

import java.util.Optional;

public interface UsuarioIn {

    Optional<UsuarioDto> CrearUsuario(UsuarioDto usuario);

}
