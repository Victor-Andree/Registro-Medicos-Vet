package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.CitaDto;
import webvet.v1.domain.aggregates.model.Cita;
import webvet.v1.domain.ports.output.CitaOut;
import webvet.v1.infraestructure.entity.*;
import webvet.v1.infraestructure.mapper.*;
import webvet.v1.infraestructure.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaAdapter implements CitaOut {

    private final CitaMapper citaMapper;
    private final CitaRepository citaRepository;
    private final ClienteMapper clienteMapper;
    private final TipoServicioMapper tipoServicioMapper;
    private final MascotaMapper mascotaMapper;
    private final VeterinarioMapper veterinarioMapper;
    private final TipoServicioRepository tipoServicioRepository;
    private final ClienteRepository clienteRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;


    public CitaAdapter(CitaMapper citaMapper, CitaRepository citaRepository, ClienteMapper clienteMapper, TipoServicioMapper tipoServicioMapper, MascotaMapper mascotaMapper, VeterinarioMapper veterinarioMapper, TipoServicioRepository tipoServicioRepository, ClienteRepository clienteRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaMapper = citaMapper;
        this.citaRepository = citaRepository;
        this.clienteMapper = clienteMapper;
        this.tipoServicioMapper = tipoServicioMapper;
        this.mascotaMapper = mascotaMapper;
        this.veterinarioMapper = veterinarioMapper;
        this.tipoServicioRepository = tipoServicioRepository;
        this.clienteRepository = clienteRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }


    @Override
    public Optional<CitaDto> createCita (Cita cita){

        CitaEntity citaEntity = citaMapper.toCitaEntity(cita);

        TipoServicioEntity tipoServicio = tipoServicioRepository.getReferenceById(cita.getTipoServicio().getTipoServicioId());
        ClienteEntity cliente = clienteRepository.getReferenceById(cita.getCliente().getClienteId());
        MascotaEntity mascota = mascotaRepository.getReferenceById(cita.getMascota().getMascotaId());
        VeterinarioEntity veterinario = veterinarioRepository.getReferenceById(cita.getVeterinario().getVeterinarioId());

        citaEntity.setTipoServicio(tipoServicio);
        citaEntity.setCliente(cliente);
        citaEntity.setMascota(mascota);
        citaEntity.setVeterinario(veterinario);

        CitaEntity citaCreate = citaRepository.save(citaEntity);

        return Optional.of(citaMapper.toCitaDtoFromEntity(citaCreate));
    }

    @Override
    public List<Cita> getAllCitas(){
        List<CitaEntity> citaEntities = citaRepository.findAll();

        return citaEntities.stream()
                .map(citaMapper::toCita)
                .collect(Collectors.toList());

    }

    @Override
    public List<Cita> getCitasByClienteId(Long clienteId){

        List<CitaEntity> citaEntities = citaRepository.findByCliente_ClienteId(clienteId);
        return citaEntities.stream()
                .map(citaMapper::toCita)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> getCitaByFecha(LocalDate fechaRegistro){
        LocalDateTime start = fechaRegistro.atStartOfDay();
        LocalDateTime end = fechaRegistro.atTime(LocalTime.MAX);

        List<CitaEntity> citaEntities = citaRepository.findByFechaRegistroBetween(start, end);

        return citaEntities.stream()
                .map(citaMapper::toCita)
                .collect(Collectors.toList());

    }

    @Override
    public  Optional<Cita> updateCita(Cita cita){
        Optional<CitaEntity> citaEntityOpt = citaRepository.findById(cita.getCitaId());

        if (citaEntityOpt.isEmpty()) {
            return Optional.empty(); // No se encontró para actualizar
        }

        CitaEntity citaEntity = citaEntityOpt.get();

        // Actualizar campos
        citaEntity.setFechaRegistro(cita.getFechaRegistro());
        citaEntity.setMotivo(cita.getMotivo());

        // Mapeos de objetos relacionados
        citaEntity.setTipoServicio(tipoServicioMapper.toTipoServicioEntity(cita.getTipoServicio()));
        citaEntity.setMascota(mascotaMapper.toMascotaEntity(cita.getMascota()));
        citaEntity.setCliente(clienteMapper.toClienteEntity(cita.getCliente()));
        citaEntity.setVeterinario(veterinarioMapper.toVeterinarioEntity(cita.getVeterinario()));

        // Guardar
        CitaEntity updated = citaRepository.save(citaEntity);

        return Optional.of(citaMapper.toCita(updated));

    }

    @Override
    public Optional<Cita> foundCitaById(Long citaId){
        return citaRepository.findById(citaId)
                .map(citaMapper::toCita);
    }


}
