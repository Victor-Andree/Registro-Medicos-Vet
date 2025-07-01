package webvet.v1.application.dto;

public class CasoClinicoDto {


    private Long casoClinicoId;

    private String descripcion;

    private Long mascotaId;

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

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }
}
