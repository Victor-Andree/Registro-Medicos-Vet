package webvet.v1.application.dto;

import webvet.v1.domain.aggregates.model.SignoVital;

import java.util.List;

public class VisitaDto {

    private Long visitaId;

    private Long casoClinicoId;

    private List<SignoVital> signoVital;

    private Long tipoVisitaId;

    public Long getVisitaId() {
        return visitaId;
    }

    public void setVisitaId(Long visitaId) {
        this.visitaId = visitaId;
    }

    public Long getCasoClinicoId() {
        return casoClinicoId;
    }

    public void setCasoClinicoId(Long casoClinicoId) {
        this.casoClinicoId = casoClinicoId;
    }

    public List<SignoVital> getSignoVital() {
        return signoVital;
    }

    public void setSignoVital(List<SignoVital> signoVital) {
        this.signoVital = signoVital;
    }

    public Long getTipoVisitaId() {
        return tipoVisitaId;
    }

    public void setTipoVisitaId(Long tipoVisitaId) {
        this.tipoVisitaId = tipoVisitaId;
    }
}
