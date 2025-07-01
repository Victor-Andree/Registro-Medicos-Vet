package webvet.v1.domain.aggregates.model;

public class CasoClinico {

    private Long casoClinicoId;

    private String descripcion;

    private Mascota mascota;

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

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
