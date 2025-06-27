package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.UsuarioDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioIn {

    Optional<UsuarioDto> CrearUsuario(UsuarioDto usuario);
    Optional<UsuarioDto> findUsuarioByUsername(String username);
    Optional<UsuarioDto> desactivarUsuario(int usuarioId);
    Optional<UsuarioDto> actualizarUsuario(UsuarioDto usuarioDto);
    List<UsuarioDto> listarUsuarios();



}
