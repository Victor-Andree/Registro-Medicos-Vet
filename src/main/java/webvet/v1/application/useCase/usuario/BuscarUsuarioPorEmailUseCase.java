package webvet.v1.application.useCase.usuario;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.input.usuario.BuscarUsuarioPorEmailOut;
import webvet.v1.domain.ports.output.UsuarioOut;
import webvet.v1.infraestructure.mapper.UsuarioMapper;

import java.util.Optional;


@Service
public class BuscarUsuarioPorEmailUseCase implements BuscarUsuarioPorEmailOut {

    private final UsuarioOut usuarioOut;
    private final UsuarioMapper usuarioMapper;

    public BuscarUsuarioPorEmailUseCase(UsuarioOut usuarioOut, UsuarioMapper usuarioMapper) {
        this.usuarioOut = usuarioOut;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Optional<UsuarioDto> buscarUsuarioPorEmail(String email) {
      Optional<Usuario> user =  usuarioOut.findUsuarioByEmail(email);

        return user.map(usuarioMapper::toUsuarioDto);
    }

}
