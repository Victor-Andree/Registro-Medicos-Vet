package webvet.v1.infraestructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.constans.EstadoUsuario;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.output.UsuarioOut;
import webvet.v1.infraestructure.entity.UsuarioEntity;
import webvet.v1.infraestructure.mapper.UsuarioMapper;
import webvet.v1.infraestructure.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioAdapter implements UsuarioOut {

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario){

        UsuarioEntity usuarioEntity = usuarioMapper.toUsuarioEntity(usuario);
        UsuarioEntity usuarioEntityCreate = usuarioRepository.save(usuarioEntity);

        return Optional.of(usuarioMapper.toUsuario(usuarioEntityCreate));


    }

    @Override
    public Optional<Usuario> findUsuarioByUsername(String username) {
       Optional<UsuarioEntity> user = usuarioRepository.findByUsername(username);
        return user.map(usuarioMapper::toUsuario);
    }

    @Override
    public Optional<Usuario> actualizarUsuario(Usuario usuario) {
        Optional<UsuarioEntity> entityOpt = usuarioRepository.findById(usuario.getUsuarioId());
        if (entityOpt.isEmpty()) return Optional.empty();

        UsuarioEntity entity = entityOpt.get();
        entity.setUsername(usuario.getUsername());
        entity.setPassword(usuario.getPassword());
        entity.setRol(usuario.getRol());
        entity.setEstado(usuario.getEstado());
        entity.setFechaRegistro(usuario.getFechaRegistro());

        UsuarioEntity updated = usuarioRepository.save(entity);
        return Optional.of(usuarioMapper.toUsuario(updated));
    }

    @Override
    public Optional<Usuario> findUsuarioById(int usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .map(usuarioMapper::toUsuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<UsuarioEntity> entities = usuarioRepository.findAll();
        return entities.stream()
                .map(usuarioMapper::toUsuario)
                .collect(Collectors.toList());
    }





}
