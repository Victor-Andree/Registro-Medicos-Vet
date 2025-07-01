package webvet.v1.domain.aggregates.model;

public class TipoVisita {

    private Long tipoVisitaId;

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
