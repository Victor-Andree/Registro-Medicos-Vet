package webvet.v1.domain.aggregates.model;

import java.util.List;

public class Visita {

    private Long visitaId;

    private CasoClinico casoClinico;

    private List<SignoVital> signoVital;

    private TipoVisita tipoVisita;

    public Long getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(Long visitaId) {
        this.visitaId = visitaId;
    }

    public CasoClinico getCasoClinico() {
        return casoClinico;
    }

    public void setCasoClinico(CasoClinico casoClinico) {
        this.casoClinico = casoClinico;
    }

    public List<SignoVital> getSignoVital() {
        return signoVital;
    }

    public void setSignoVital(List<SignoVital> signoVital) {
        this.signoVital = signoVital;
    }

    public TipoVisita getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(TipoVisita tipoVisita) {
        this.tipoVisita = tipoVisita;
    }
}
