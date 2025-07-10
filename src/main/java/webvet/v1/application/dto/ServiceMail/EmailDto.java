package webvet.v1.application.dto.ServiceMail;

public class EmailDto {

    private String nombreDueño;

    private String emailDueño;

    private String mascotaNombre;

    private String fecha;

    private String hora;

    private String VeterinarioNombre;

    public String getNombreDueño() {
        return nombreDueño;
    }

    public void setNombreDueño(String nombreDueño) {
        this.nombreDueño = nombreDueño;
    }

    public String getEmailDueño() {
        return emailDueño;
    }

    public void setEmailDueño(String emailDueño) {
        this.emailDueño = emailDueño;
    }

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getVeterinarioNombre() {
        return VeterinarioNombre;
    }

    public void setVeterinarioNombre(String veterinarioNombre) {
        VeterinarioNombre = veterinarioNombre;
    }
}
