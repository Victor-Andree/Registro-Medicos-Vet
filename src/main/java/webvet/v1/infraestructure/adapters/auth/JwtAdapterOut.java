package webvet.v1.infraestructure.adapters.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.output.service.JwtServiceOut;
import webvet.v1.infraestructure.entity.UsuarioEntity;
import webvet.v1.infraestructure.services.JwtService;

import java.util.Set;

@Service
public class JwtAdapterOut implements JwtServiceOut {

    final private JwtService jwtService;

    public JwtAdapterOut(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String generateToken(UsuarioEntity usuarioEntity) {
        return jwtService.generateToken(new User(usuarioEntity.getUsername(), usuarioEntity.getPassword(), Set.of()));
    }
}
