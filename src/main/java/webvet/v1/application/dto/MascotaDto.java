package webvet.v1.application.dto;


public class MascotaDto {

    private Long mascotaId;

    private String nombre;

    private int edad;

    private Long razaId;

    private Long clienteId;

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {return edad;}

    public void setEdad(int edad) {this.edad = edad;}

    public Long getRazaId() {return razaId;}

    public void setRazaId(Long razaId) {this.razaId = razaId;}

    public Long getClienteId() {return clienteId;}

    public void setClienteId(Long clienteId) {this.clienteId = clienteId;}
}
