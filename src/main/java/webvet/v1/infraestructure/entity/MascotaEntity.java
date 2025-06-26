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

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "raza_id", nullable = false)
    private RazaEntity raza;

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

    public Integer getEdad() {return edad;}

    public void setEdad(Integer edad) {this.edad = edad;}

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public TriajeEntity getTriaje() {
        return triaje;
    }

    public void setTriaje(TriajeEntity triaje) {
        this.triaje = triaje;
    }

    public RazaEntity getRaza() {return raza;}

    public void setRaza(RazaEntity raza) {this.raza = raza;}
}
