package webvet.v1.domain.ports.input.auth;

import webvet.v1.domain.aggregates.model.Usuario;

public interface GenerarTokenOut {
    String generarToken(Usuario usuario);
}
