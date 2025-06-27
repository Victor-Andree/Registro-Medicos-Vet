package webvet.v1.application.dto;

import webvet.v1.domain.aggregates.constans.EstadoUsuario;
import webvet.v1.domain.aggregates.constans.RolEnum;

import java.time.LocalDateTime;

public class UsuarioDto {

    private Integer usuarioId;

    private String username;

    private String password;

    private EstadoUsuario estado;

    private LocalDateTime fechaRegistro;

    private RolEnum rol;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }

    public EstadoUsuario getEstado() {return estado;}

    public void setEstado(EstadoUsuario estado) {this.estado = estado;}

    public LocalDateTime getFechaRegistro() {return fechaRegistro;}

    public void setFechaRegistro(LocalDateTime fechaRegistro) {this.fechaRegistro = fechaRegistro;}
}
