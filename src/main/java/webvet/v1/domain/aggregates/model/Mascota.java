package webvet.v1.domain.aggregates.model;

public class Mascota {

    private Integer mascotaId;

    private String nombre;

    private String raza;

    private String especie;

    private float peso;

    private Cliente cliente;


    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Integer mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
