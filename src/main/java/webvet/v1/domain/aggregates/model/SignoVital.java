package webvet.v1.domain.aggregates.model;

public class SignoVital {

    private Long signoVitalId;

    private TipoSignoVital tipoSignoVital;

    private Double valor;

    public Long getSignoVitalId() {
        return signoVitalId;
    }

    public void setSignoVitalId(Long signoVitalId) {
        this.signoVitalId = signoVitalId;
    }

    public TipoSignoVital getTipoSignoVital() {
        return tipoSignoVital;
    }

    public void setTipoSignoVital(TipoSignoVital tipoSignoVital) {
        this.tipoSignoVital = tipoSignoVital;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
