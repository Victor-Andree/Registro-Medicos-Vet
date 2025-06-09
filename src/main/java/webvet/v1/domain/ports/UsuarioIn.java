package webvet.v1.domain.ports;

import webvet.v1.application.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioIn {

    Optional<UsuarioDto> CrearUsuario(UsuarioDto usuario);

}
