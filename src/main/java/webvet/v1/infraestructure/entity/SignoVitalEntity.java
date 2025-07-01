package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "SignoVital")
public class SignoVitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long signoVitalId;

    @ManyToOne
    @JoinColumn (name = "tipoSignoVitalId")
    private TipoSignoVitalEntity tipoSignoVital;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "visita_id")
    private VisitaEntity visita;

    public Long getSignoVitalId() {
        return signoVitalId;
    }

    public void setSignoVitalId(Long signoVitalId) {
        this.signoVitalId = signoVitalId;
    }

    public TipoSignoVitalEntity getTipoSignoVital() {
        return tipoSignoVital;
    }

    public void setTipoSignoVital(TipoSignoVitalEntity tipoSignoVital) {
        this.tipoSignoVital = tipoSignoVital;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public VisitaEntity getVisita() {
        return visita;
    }

    public void setVisita(VisitaEntity visita) {
        this.visita = visita;
    }
}
