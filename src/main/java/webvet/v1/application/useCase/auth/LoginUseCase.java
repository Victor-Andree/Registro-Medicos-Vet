package webvet.v1.application.useCase.auth;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.request.LoginRequest;
import webvet.v1.application.dto.response.AuthenticationResponse;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.input.auth.LoginUsuarioOut;
import webvet.v1.domain.ports.output.UsuarioOut;
import webvet.v1.domain.ports.output.auth.AuthenticationOut;
import webvet.v1.domain.ports.output.service.JwtServiceOut;
import webvet.v1.infraestructure.mapper.UsuarioMapper;

import java.util.Optional;

@Service
public class LoginUseCase implements LoginUsuarioOut {

    private final AuthenticationOut authenticationOut;
    private final UsuarioOut usuarioOut;
    private final JwtServiceOut jwtService;
    private final UsuarioMapper usuarioMapper;

    public LoginUseCase(AuthenticationOut authenticationOut, UsuarioOut usuarioOut, JwtServiceOut jwtService, UsuarioMapper usuarioMapper) {
        this.authenticationOut = authenticationOut;
        this.usuarioOut = usuarioOut;
        this.jwtService = jwtService;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {

        Usuario userExist = usuarioOut.findUsuarioByEmail(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        userExist.setPassword(loginRequest.getPassword());

        authenticationOut.login(usuarioMapper.toUsuarioEntity(userExist));

        String token = jwtService.generateToken(usuarioMapper.toUsuarioEntity(userExist));

        return new AuthenticationResponse(token, userExist.getUsername());


    }
}
