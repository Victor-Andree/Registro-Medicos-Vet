package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "TipoSignoVital")
public class TipoSignoVitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoSignoVitalId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Long getTipoSignoVitalId() {
        return tipoSignoVitalId;
    }

    public void setTipoSignoVitalId(Long tipoSignoVitalId) {
        this.tipoSignoVitalId = tipoSignoVitalId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
