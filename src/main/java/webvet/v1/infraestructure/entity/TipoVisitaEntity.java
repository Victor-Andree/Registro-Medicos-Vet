package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "TipoVisita")
public class TipoVisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoVisitaId;

    @Column(name = "nombre")
    private String nombre;

    public Long getTipoVisitaId() {
        return tipoVisitaId;
    }

    public void setTipoVisitaId(Long tipoVisitaId) {
        this.tipoVisitaId = tipoVisitaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
