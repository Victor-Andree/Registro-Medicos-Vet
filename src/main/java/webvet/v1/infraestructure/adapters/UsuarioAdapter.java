package webvet.v1.infraestructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.output.UsuarioOut;
import webvet.v1.infraestructure.entity.UsuarioEntity;
import webvet.v1.infraestructure.mapper.UsuarioMapper;
import webvet.v1.infraestructure.repository.UsuarioRepository;

import java.util.Optional;

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
    public Optional<Usuario> findUsuarioByEmail(String email) {
       Optional<UsuarioEntity> user = usuarioRepository.findByUsername(email);
        return user.map(usuarioMapper::toUsuario);
    }


}
