package webvet.v1.domain.ports.output.auth;

import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.infraestructure.entity.UsuarioEntity;

public interface AuthenticationOut {

    Usuario registrarUsuario(Usuario trabajador);

    void login(UsuarioEntity usuarioEntity);



}
