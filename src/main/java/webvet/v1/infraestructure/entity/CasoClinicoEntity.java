package webvet.v1.infraestructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CasoClinico")
public class CasoClinicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long casoClinicoId;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private MascotaEntity mascota;

    public Long getCasoClinicoId() {
        return casoClinicoId;
    }

    public void setCasoClinicoId(Long casoClinicoId) {
        this.casoClinicoId = casoClinicoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }
}
