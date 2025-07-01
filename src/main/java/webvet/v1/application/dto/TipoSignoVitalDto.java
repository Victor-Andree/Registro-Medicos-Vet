package webvet.v1.application.dto;

public class TipoSignoVitalDto {

    private Long tipoSignoVitalId;

    private String nombre;

    public Long getTipoSignoVitalId() {
        return tipoSignoVitalId;
    }

    public void setTipoSignoVitalId(Long tipoSignoVitalId) {
        this.tipoSignoVitalId = tipoSignoVitalId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
