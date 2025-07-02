package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.CitaDto;
import webvet.v1.domain.aggregates.model.Cita;
import webvet.v1.domain.ports.input.CitaIn;
import webvet.v1.domain.ports.output.CitaOut;
import webvet.v1.infraestructure.mapper.CitaMapper;
import webvet.v1.infraestructure.repository.CitaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaUseCase implements CitaIn {

    private final CitaOut citaOut;
    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;


    public CitaUseCase(CitaOut citaOut, CitaRepository citaRepository, CitaMapper citaMapper) {
        this.citaOut = citaOut;
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
    }

    @Override
    public Optional<CitaDto> createCita (CitaDto citaDto){
        Cita cita = citaMapper.toCitaFromDto(citaDto);

        Optional<Cita> citaCreada = citaOut.createCita(cita);
        return citaCreada.map(citaMapper::toCitaDto);
    }

    @Override
    public List<CitaDto> getAllCitas(){
        List<Cita>  cita = citaOut.getAllCitas();

        if (cita.isEmpty()){
            throw new EntityNotFoundException("No se encontraron citas registradas");
        }

        return citaMapper.toCitaDtoList(cita);
    }

    @Override
    public List<CitaDto> getCitasByClienteId(Long clienteId){
        List<Cita> citas = citaOut.getCitasByClienteId(clienteId);
        return citas.stream()
                .map(citaMapper::toCitaDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaDto> getCitaByFecha(LocalDate fechaRegistro){
        List<Cita> citas = citaOut.getCitaByFecha(fechaRegistro);

        if (citas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron citas para la fecha: " + fechaRegistro);
        }

        return citas.stream()
                .map(citaMapper::toCitaDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CitaDto> updateCita(CitaDto citaDto){
        Cita cita = citaMapper.toCitaFromDto(citaDto);

        Optional<Cita> citaActualizada = citaOut.updateCita(cita);

        return citaActualizada.map(citaMapper::toCitaDto);
    }
}
