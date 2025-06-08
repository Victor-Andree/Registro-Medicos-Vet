package webvet.v1.domain.ports.output.service;

import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.infraestructure.entity.UsuarioEntity;

public interface JwtServiceOut {
    String generateToken(UsuarioEntity usuarioEntity);
}
