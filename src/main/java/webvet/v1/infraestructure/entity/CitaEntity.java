package webvet.v1.infraestructure.entity;


import jakarta.persistence.*;
import webvet.v1.domain.aggregates.constans.EstadoCita;

import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "cita_id")
    private Long citaId;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio", nullable = false)
    private TipoServicioEntity tipoServicio;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private MascotaEntity mascota;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private VeterinarioEntity veterinario;

    @Column(name = "motivo", length = 500)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cita", nullable = false)
    private EstadoCita estadoCita;

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TipoServicioEntity getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicioEntity tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public MascotaEntity getMascota() {
        return mascota;
    }

    public void setMascota(MascotaEntity mascota) {
        this.mascota = mascota;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public VeterinarioEntity getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(VeterinarioEntity veterinario) {
        this.veterinario = veterinario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }
}
