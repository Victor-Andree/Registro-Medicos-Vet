package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "TipoServicio")
public class TipoServicioEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "tiposervicio_id")
    private Long tiposervicioId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Long getTiposervicioId() {
        return tiposervicioId;
    }

    public void setTiposervicioId(Long tiposervicioId) {
        this.tiposervicioId = tiposervicioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
