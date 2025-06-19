package webvet.v1.application.useCase.usuario;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.input.usuario.BuscarUsuarioPorUsernameOut;
import webvet.v1.domain.ports.output.UsuarioOut;
import webvet.v1.infraestructure.mapper.UsuarioMapper;

import java.util.Optional;


@Service
public class BuscarUsuarioPorUsernameUseCase implements BuscarUsuarioPorUsernameOut {

    private final UsuarioOut usuarioOut;
    private final UsuarioMapper usuarioMapper;

    public BuscarUsuarioPorUsernameUseCase(UsuarioOut usuarioOut, UsuarioMapper usuarioMapper) {
        this.usuarioOut = usuarioOut;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Optional<UsuarioDto> findUsuarioByUsername(String username) {
      Optional<Usuario> user =  usuarioOut.findUsuarioByUsername(username);

        return user.map(usuarioMapper::toUsuarioDto);
    }

}
