package webvet.v1.application.dto;

public class SignoVitalDto {

    private Long signoVitalId;

    private  Long tipoSignoVitalId;

    private Double valor;

    public Long getSignoVitalId() {
        return signoVitalId;
    }

    public void setSignoVitalId(Long signoVitalId) {
        this.signoVitalId = signoVitalId;
    }

    public Long getTipoSignoVitalId() {
        return tipoSignoVitalId;
    }

    public void setTipoSignoVitalId(Long tipoSignoVitalId) {
        this.tipoSignoVitalId = tipoSignoVitalId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
