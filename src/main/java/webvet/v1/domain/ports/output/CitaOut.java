package webvet.v1.domain.ports.output;

import webvet.v1.application.dto.CitaDto;
import webvet.v1.domain.aggregates.constans.EstadoCita;
import webvet.v1.domain.aggregates.model.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaOut {

    Optional<CitaDto> createCita(Cita cita);

    List<Cita> getAllCitas();

    List<Cita> getCitasByClienteId(Long clienteId);

    List<Cita> getCitaByFecha(LocalDate fechaRegistro);

    Optional<Cita> updateCita(Cita cita);

    Optional<Cita> foundCitaById(Long citaId);

    List<Cita> getAllCitasByToday();

    Optional<Cita> updateEstadoCita(Long citaId, EstadoCita nuevoEstado);




}
