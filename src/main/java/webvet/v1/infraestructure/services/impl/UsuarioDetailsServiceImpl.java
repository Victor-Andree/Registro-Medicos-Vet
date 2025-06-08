package webvet.v1.infraestructure.services.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.input.usuario.BuscarUsuarioPorEmailOut;
import webvet.v1.infraestructure.mapper.UsuarioMapper;

import java.util.Set;

@Component
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final BuscarUsuarioPorEmailOut buscarUsuarioPorEmailOut;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDetailsServiceImpl(BuscarUsuarioPorEmailOut buscarUsuarioPorEmailOut, UsuarioMapper usuarioMapper) {
        this.buscarUsuarioPorEmailOut = buscarUsuarioPorEmailOut;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDto dto = buscarUsuarioPorEmailOut.buscarUsuarioPorEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));

        Usuario usuario = usuarioMapper.toUsuarioFromtDto(dto);
        System.out.println("Usuario encontrado: " + usuario.getUsername());
        System.out.println("Contraseña almacenada: " + usuario.getPassword());

        Set<GrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority(usuario.getRol().name())
        );

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }


}
