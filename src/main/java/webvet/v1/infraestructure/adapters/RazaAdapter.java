package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.RazaDto;
import webvet.v1.domain.aggregates.model.Raza;
import webvet.v1.domain.ports.output.RazaOut;
import webvet.v1.infraestructure.entity.RazaEntity;
import webvet.v1.infraestructure.mapper.RazaMapper;
import webvet.v1.infraestructure.repository.RazaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RazaAdapter implements RazaOut {

    private final RazaMapper razaMapper;
    private final RazaRepository razaRepository;


    public RazaAdapter(RazaMapper razaMapper, RazaRepository razaRepository) {
        this.razaMapper = razaMapper;
        this.razaRepository = razaRepository;
    }


    @Override
    public Optional<Raza> createRaza (Raza raza) {

        RazaEntity razaEntity = razaMapper.toRazaEntity(raza);
        razaRepository.save(razaEntity);

        return Optional.of(razaMapper.toRaza(razaEntity));

    }

    @Override
    public List<Raza> getAllRazas() {
        List<RazaEntity> razaEntities = razaRepository.findAll();

        return razaEntities.stream()
                .map(razaMapper::toRaza)
                .collect(Collectors.toList());

    }
}
