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

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEnitity cliente;

    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL)
    private TriajeEntity triaje;


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

    public TriajeEntity getTriaje() {
        return triaje;
    }

    public void setTriaje(TriajeEntity triaje) {
        this.triaje = triaje;
    }
}
