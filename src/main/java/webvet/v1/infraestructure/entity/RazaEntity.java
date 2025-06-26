package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "razas")
public class RazaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long razaId;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private EspecieEntity especie;

    public Long getRazaId() {
        return razaId;
    }

    public void setRazaId(Long razaId) {
        this.razaId = razaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EspecieEntity getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieEntity especie) {
        this.especie = especie;
    }
}
