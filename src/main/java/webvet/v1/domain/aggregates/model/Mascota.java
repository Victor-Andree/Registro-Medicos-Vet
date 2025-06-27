package webvet.v1.domain.aggregates.model;

import webvet.v1.domain.aggregates.constans.EstadoMascota;

public class Mascota {

    private Long mascotaId;

    private String nombre;

    private int edad;

    private EstadoMascota estado;

    private Raza raza;

    private Cliente cliente;


    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {return edad;}

    public void setEdad(int edad) {this.edad = edad;}

    public Raza getRaza() {return raza;}

    public void setRaza(Raza raza) {this.raza = raza;}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoMascota getEstado() {return estado;}

    public void setEstado(EstadoMascota estado) {this.estado = estado;}
}
