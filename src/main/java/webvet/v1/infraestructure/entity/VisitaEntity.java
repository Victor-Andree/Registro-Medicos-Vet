package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Visita")
public class VisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitaId;

    @ManyToOne
    @JoinColumn(name = "caso_clinico_id", nullable = false)
    private CasoClinicoEntity casoClinico;

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL)
    private List<SignoVitalEntity> signoVital ;

    @ManyToOne
    @JoinColumn(name = "tipo_visita_id")
    private TipoVisitaEntity tipoVisita;


    public Long getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(Long visitaId) {
        this.visitaId = visitaId;
    }

    public CasoClinicoEntity getCasoClinico() {
        return casoClinico;
    }

    public void setCasoClinico(CasoClinicoEntity casoClinico) {
        this.casoClinico = casoClinico;
    }

    public List<SignoVitalEntity> getSignoVital() {
        return signoVital;
    }

    public void setSignoVital(List<SignoVitalEntity> signoVital) {
        this.signoVital = signoVital;
    }

    public TipoVisitaEntity getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(TipoVisitaEntity tipoVisita) {
        this.tipoVisita = tipoVisita;
    }
}
