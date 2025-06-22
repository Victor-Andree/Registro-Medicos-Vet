package webvet.v1.application.useCase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.input.UsuarioIn;
import webvet.v1.domain.ports.output.UsuarioOut;

import java.util.Optional;


@Service
public class UsuarioUseCase implements UsuarioIn {

    private final UsuarioOut usuarioOut;


    private final PasswordEncoder passwordEncoder;

    public UsuarioUseCase(UsuarioOut usuarioOut, PasswordEncoder passwordEncoder) {
        this.usuarioOut = usuarioOut;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<UsuarioDto> CrearUsuario(UsuarioDto usuarioDto){

        Optional<Usuario> usuarioExistente = usuarioOut.findUsuarioByUsername(usuarioDto.getUsername());

        if (usuarioExistente.isPresent()) {
            return Optional.empty();
        }

        String passwordEncriptada = passwordEncoder.encode(usuarioDto.getPassword());

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usuarioDto.getUsername());
        nuevoUsuario.setPassword(passwordEncriptada);
        nuevoUsuario.setRol(usuarioDto.getRol()); // ← AQUÍ USAMOS EL ROL QUE LLEGA EN EL JSON

        Optional<Usuario> usuarioCreado = usuarioOut.crearUsuario(nuevoUsuario);

        return usuarioCreado.map(user -> {
            UsuarioDto dto = new UsuarioDto();
            dto.setUsuarioId(user.getUsuarioId());
            dto.setUsername(user.getUsername());
            dto.setRol(user.getRol());
            return dto;
        });

    }

    @Override
    public Optional<UsuarioDto> findUsuarioByUsername(String username) {
        Optional<Usuario> usuarioExistente = usuarioOut.findUsuarioByUsername(username);

        return usuarioExistente.map(usuario -> {
            UsuarioDto dto = new UsuarioDto();
            dto.setUsuarioId(usuario.getUsuarioId());
            dto.setUsername(usuario.getUsername());
            dto.setRol(usuario.getRol());
            return dto;
        });
    }


}
