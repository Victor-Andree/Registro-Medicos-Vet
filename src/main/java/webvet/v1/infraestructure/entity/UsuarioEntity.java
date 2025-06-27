package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;
import webvet.v1.domain.aggregates.constans.EstadoUsuario;
import webvet.v1.domain.aggregates.constans.RolEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")

public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    private LocalDateTime fechaRegistro;

    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
