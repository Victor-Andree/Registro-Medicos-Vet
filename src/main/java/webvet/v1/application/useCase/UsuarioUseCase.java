package webvet.v1.application.useCase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.constans.EstadoUsuario;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.input.UsuarioIn;
import webvet.v1.domain.ports.output.UsuarioOut;
import webvet.v1.infraestructure.mapper.UsuarioMapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioUseCase implements UsuarioIn {

    private final UsuarioOut usuarioOut;


    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper;

    public UsuarioUseCase(UsuarioOut usuarioOut, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioOut = usuarioOut;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
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
        nuevoUsuario.setEstado(EstadoUsuario.ACTIVO);
        nuevoUsuario.setFechaRegistro(LocalDateTime.now(ZoneId.of("America/Lima")));

        Optional<Usuario> usuarioCreado = usuarioOut.crearUsuario(nuevoUsuario);

        return usuarioCreado.map(user -> {
            UsuarioDto dto = new UsuarioDto();
            dto.setUsuarioId(user.getUsuarioId());
            dto.setUsername(user.getUsername());
            dto.setRol(user.getRol());
            dto.setEstado(user.getEstado());
            dto.setFechaRegistro(user.getFechaRegistro());
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

    @Override
    public Optional<UsuarioDto> desactivarUsuario(int usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioOut.findUsuarioById(usuarioId);
        if (usuarioOpt.isEmpty()) return Optional.empty();

        Usuario usuario = usuarioOpt.get();
        usuario.setEstado(EstadoUsuario.INACTIVO);

        return usuarioOut.actualizarUsuario(usuario)
                .map(usuarioMapper::toUsuarioDto);
    }

    @Override
    public Optional<UsuarioDto> actualizarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.toUsuarioFromtDto(usuarioDto);
        return usuarioOut.actualizarUsuario(usuario)
                .map(usuarioMapper::toUsuarioDto);
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioOut.listarUsuarios();
        return usuarios.stream()
                .map(usuarioMapper::toUsuarioDto)
                .collect(Collectors.toList());
    }




}
