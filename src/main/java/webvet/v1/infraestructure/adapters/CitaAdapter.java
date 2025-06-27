package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Cita;
import webvet.v1.domain.ports.output.CitaOut;
import webvet.v1.infraestructure.entity.CitaEntity;
import webvet.v1.infraestructure.entity.MascotaEntity;
import webvet.v1.infraestructure.mapper.*;
import webvet.v1.infraestructure.repository.CitaRepository;

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


    public CitaAdapter(CitaMapper citaMapper, CitaRepository citaRepository, ClienteMapper clienteMapper, TipoServicioMapper tipoServicioMapper, MascotaMapper mascotaMapper, VeterinarioMapper veterinarioMapper) {
        this.citaMapper = citaMapper;
        this.citaRepository = citaRepository;
        this.clienteMapper = clienteMapper;
        this.tipoServicioMapper = tipoServicioMapper;
        this.mascotaMapper = mascotaMapper;
        this.veterinarioMapper = veterinarioMapper;
    }


    @Override
    public Optional<Cita> createCita (Cita cita){

        CitaEntity citaEntity = citaMapper.toCitaEntity(cita);
        CitaEntity citaCreate = citaRepository.save(citaEntity);


        return Optional.of(citaMapper.toCita(citaEntity));
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


}
