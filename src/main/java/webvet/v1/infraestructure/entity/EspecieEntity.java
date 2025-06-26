package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "especies")
public class EspecieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long especieId;

    @Column(nullable = false)
    private String nombre;

    public Long getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Long especieId) {
        this.especieId = especieId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
