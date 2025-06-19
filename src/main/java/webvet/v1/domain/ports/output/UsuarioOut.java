package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Usuario;

import java.util.Optional;

public interface UsuarioOut {
  Optional<Usuario> crearUsuario(Usuario usuario);
  Optional<Usuario> findUsuarioByUsername(String username);
}
