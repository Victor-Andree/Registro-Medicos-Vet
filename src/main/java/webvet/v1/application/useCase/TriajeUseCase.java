package webvet.v1.application.useCase;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.TriajeDto;
import webvet.v1.domain.aggregates.model.Triaje;
import webvet.v1.domain.ports.input.TriajeIn;
import webvet.v1.domain.ports.output.TriajeOut;
import webvet.v1.infraestructure.mapper.TriajeMapper;
import webvet.v1.infraestructure.repository.TriajeRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TriajeUseCase implements TriajeIn {

    private final TriajeMapper triajeMapper;

    private final TriajeRepository triajeRepository;

    private final TriajeOut triajeOut;

    public TriajeUseCase(TriajeMapper triajeMapper, TriajeRepository triajeRepository, TriajeOut triajeOut) {
        this.triajeMapper = triajeMapper;
        this.triajeRepository = triajeRepository;
        this.triajeOut = triajeOut;
    }

    @Override
    public  Optional<TriajeDto> createTriaje (TriajeDto triajeDto){

        Triaje triaje = triajeMapper.toTriajeFromDto(triajeDto);

        Optional<Triaje> triajeCreado = triajeOut.createTriaje(triaje);

        triaje.setFechaRegistro(LocalDateTime.now(ZoneId.of("America/Lima")));
        return triajeCreado.map(triajeMapper::toTriajeDto);

    }

    @Override
    public List<TriajeDto> findTriajeByMascota(Long mascotaId){
        List<Triaje> triajes = triajeOut.findTriajeByMascota(mascotaId);
        return triajes.stream()
                .map(triajeMapper::toTriajeDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TriajeDto> updateTriaje (TriajeDto triajeDto){

        Triaje triaje = triajeMapper.toTriajeFromDto(triajeDto);

        Optional<Triaje> triajeActualizado = triajeOut.updateTriaje(triaje);

        triaje.setFechaRegistro(LocalDateTime.now(ZoneId.of("America/Lima")));

        return triajeActualizado.map(triajeMapper::toTriajeDto);
    }

    @Override
    public Optional<TriajeDto> updateTriajeByMascotaId(Long mascotaId) {
        return triajeOut.updateTriajeByMascotaId(mascotaId)
                .map(triajeMapper::toTriajeDto);
    }


}
