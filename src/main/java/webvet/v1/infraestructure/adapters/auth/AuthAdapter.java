package webvet.v1.infraestructure.adapters.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.output.auth.AuthenticationOut;
import webvet.v1.infraestructure.entity.UsuarioEntity;
import webvet.v1.infraestructure.repository.UsuarioRepository;


@Service
public class AuthAdapter implements AuthenticationOut {


    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AuthAdapter(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario registrarUsuario(Usuario trabajador) {
        // Validar si existe, mapear Usuario a UsuarioEntity, codificar contraseña, guardar
        UsuarioEntity entity = new UsuarioEntity();
        entity.setUsername("user@gmail.com");
        entity.setPassword(new BCryptPasswordEncoder().encode("123456"));
        // Rellenar otros campos necesarios

        UsuarioEntity saved = usuarioRepository.save(entity);

        // Retornar como modelo de dominio
        return new Usuario();
    }

    @Override
    public void login(UsuarioEntity usuarioEntity) {


        System.out.println("password llegando desde api");
        System.out.println("lo que llega:"+ usuarioEntity.getPassword());

//        registrarUsuario(new Usuario());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioEntity.getUsername(),
                        usuarioEntity.getPassword()
                )
        );

    }
}
