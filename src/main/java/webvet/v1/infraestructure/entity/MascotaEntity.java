package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Mascota")
public class MascotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mascota_id")
    private Long mascotaId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "raza", nullable = false)
    private String raza;

    @Column(name = "especie", nullable = false)
    private String especie;

    @Column(name = "peso", nullable = false)
    private float peso;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEnitity cliente;

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

    public ClienteEnitity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEnitity cliente) {
        this.cliente = cliente;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}
