package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.CitaDto;
import webvet.v1.domain.aggregates.model.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaIn {

    Optional<CitaDto> createCita (CitaDto citaDto);

    List<CitaDto> getAllCitas();

    List<CitaDto> getCitasByClienteId(Long clienteId);

    List<CitaDto> getCitaByFecha(LocalDate fechaRegistro);

    Optional<CitaDto> updateCita(CitaDto citaDto);

    Optional<Cita> foundCitaById(Long id);

}
