package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "triaje")
public class TriajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long triajeId;

    @Column(name = "temperatura", nullable = false)
    private Double temperatura;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "frecuenciaCardiaca", nullable = false)
    private Integer frecuenciaCardiaca;

    @Column(name = "frecuenciaRespiratoria", nullable = false)
    private Integer frecuenciaRespiratoria;

    @Column(name = "observaciones", length = 1000)
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "mascota_id", unique = true, nullable = false)
    private MascotaEntity mascota;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getTriajeId() {
        return triajeId;
    }

    public void setTriajeId(Long triajeId) {
        this.triajeId = triajeId;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frencuenciaCardiaca) {
        this.frecuenciaCardiaca = frencuenciaCardiaca;
    }

    public Integer getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(Integer frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @PrePersist
    public void setFechaRegistro() {
        if (this.fechaRegistro == null) {
            ZonedDateTime limaTime = ZonedDateTime.now(ZoneId.of("America/Lima"));

            this.fechaRegistro = limaTime.toLocalDateTime();
        }
    }

    @PreUpdate
    public void setFechaActualizacion() {
        if (this.fechaActualizacion == null) {
            ZonedDateTime limaTime = ZonedDateTime.now(ZoneId.of("America/Lima"));
            this.fechaActualizacion = limaTime.toLocalDateTime();
        }
    }
}
