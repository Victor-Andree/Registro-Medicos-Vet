package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Especie;
import webvet.v1.domain.ports.output.EspecieOut;
import webvet.v1.infraestructure.entity.EspecieEntity;
import webvet.v1.infraestructure.mapper.EspecieMapper;
import webvet.v1.infraestructure.repository.EspecieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecieAdapter implements EspecieOut {

    private final EspecieMapper especieMapper;

    private final EspecieRepository especieRepository;


    public EspecieAdapter(EspecieMapper especieMapper, EspecieRepository especieRepository) {
        this.especieMapper = especieMapper;
        this.especieRepository = especieRepository;
    }

    @Override
    public Optional<Especie> createEspecie (Especie especie) {

        EspecieEntity especieEntity = especieMapper.toEspecieEntity(especie);
        especieRepository.save(especieEntity);

        return Optional.of(especieMapper.toEspecie(especieEntity));

    }

    @Override
    public List<Especie> getAllEspecies() {
        List<EspecieEntity> especieEntities = especieRepository.findAll();

        return especieEntities.stream()
                .map(especieMapper::toEspecie)
                .collect(Collectors.toList());

    }
}
