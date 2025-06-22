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
import webvet.v1.domain.ports.input.usuario.BuscarUsuarioPorUsernameOut;
import webvet.v1.infraestructure.mapper.UsuarioMapper;

import java.util.Set;

@Component
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final BuscarUsuarioPorUsernameOut buscarUsuarioPorUsernameOut;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDetailsServiceImpl(BuscarUsuarioPorUsernameOut buscarUsuarioPorUsernameOut, UsuarioMapper usuarioMapper) {
        this.buscarUsuarioPorUsernameOut = buscarUsuarioPorUsernameOut;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDto dto = buscarUsuarioPorUsernameOut.findUsuarioByUsername(username)
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
