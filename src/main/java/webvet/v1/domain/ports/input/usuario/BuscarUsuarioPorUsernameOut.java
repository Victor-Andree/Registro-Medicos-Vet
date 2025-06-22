package webvet.v1.domain.ports.input.usuario;

import webvet.v1.application.dto.UsuarioDto;

import java.util.Optional;

public interface BuscarUsuarioPorUsernameOut {
    Optional<UsuarioDto> findUsuarioByUsername(String username);
}
