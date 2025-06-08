package webvet.v1.domain.ports.input.auth;

import webvet.v1.application.dto.request.LoginRequest;
import webvet.v1.application.dto.response.AuthenticationResponse;
import webvet.v1.domain.aggregates.model.Usuario;

public interface LoginUsuarioOut {
    AuthenticationResponse login(LoginRequest loginRequest);
}
