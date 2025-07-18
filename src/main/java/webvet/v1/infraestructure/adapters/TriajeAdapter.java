package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Triaje;
import webvet.v1.domain.ports.output.TriajeOut;
import webvet.v1.infraestructure.entity.TriajeEntity;
import webvet.v1.infraestructure.mapper.TriajeMapper;
import webvet.v1.infraestructure.repository.MascotaRepository;
import webvet.v1.infraestructure.repository.TriajeRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TriajeAdapter implements TriajeOut {


    private final TriajeMapper triajeMapper;

    private final TriajeRepository triajeRepository;

    private final MascotaRepository mascotaRepository;

    public TriajeAdapter(TriajeMapper triajeMapper, TriajeRepository triajeRepository, MascotaRepository mascotaRepository) {
        this.triajeMapper = triajeMapper;
        this.triajeRepository = triajeRepository;
        this.mascotaRepository = mascotaRepository;
    }


    @Override
    public Optional<Triaje> createTriaje(Triaje triaje) {
        TriajeEntity triajeEntity = triajeMapper.toTriajeEntity(triaje);
        TriajeEntity triajeCreate = triajeRepository.save(triajeEntity);

        return Optional.of(triajeMapper.toTriaje(triajeCreate));
    }


    @Override
    public List<Triaje> findTriajeByMascota(Long mascotaId) {

        List<TriajeEntity> triajeEntity = triajeRepository.findByMascota_MascotaId(mascotaId);
        return triajeEntity.stream()
                .map(triajeMapper::toTriaje)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Triaje> updateTriaje(Triaje triaje) {
        return triajeRepository.findById(triaje.getTriajeId())
                .map(entityExistente -> {
                    entityExistente.setTemperatura(triaje.getTemperatura());
                    entityExistente.setPeso(triaje.getPeso());
                    entityExistente.setFrecuenciaCardiaca(triaje.getFrecuenciaCardiaca());
                    entityExistente.setFrecuenciaRespiratoria(triaje.getFrecuenciaRespiratoria());


                    TriajeEntity updateEntity = triajeRepository.save(entityExistente);
                    return triajeMapper.toTriaje(updateEntity);


                });
    }

    @Override
    public Optional<Triaje> updateTriajeByMascotaId(Long mascotaId) {
        return triajeRepository.findTopByMascota_MascotaIdOrderByFechaRegistroDesc(mascotaId)
                .map(entity -> {
                    // Aquí va la lógica de actualización automática:
                    entity.setFechaRegistro(LocalDateTime.now(ZoneId.of("America/Lima")));
                    // Puedes actualizar otros campos si aplica

                    TriajeEntity actualizado = triajeRepository.save(entity);
                    return triajeMapper.toTriaje(actualizado);
                });
    }

}
