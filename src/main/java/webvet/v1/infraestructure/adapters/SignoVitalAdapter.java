package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.SignoVital;
import webvet.v1.domain.ports.output.SignoVitalOut;
import webvet.v1.infraestructure.entity.SignoVitalEntity;
import webvet.v1.infraestructure.mapper.SignoVitalMapper;
import webvet.v1.infraestructure.repository.SignoVitalRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SignoVitalAdapter implements SignoVitalOut {

    private final SignoVitalMapper signoVitalMapper;
    private final SignoVitalRepository signoVitalRepository;


    public SignoVitalAdapter(SignoVitalMapper signoVitalMapper, SignoVitalRepository signoVitalRepository) {
        this.signoVitalMapper = signoVitalMapper;
        this.signoVitalRepository = signoVitalRepository;
    }

    @Override
    public Optional<SignoVital> createSignoVital(SignoVital signoVital){

        SignoVitalEntity signoVitalEntity = signoVitalMapper.toSignoVitalEntity(signoVital);
        signoVitalRepository.save(signoVitalEntity);

        return Optional.of(signoVitalMapper.toSignoVital(signoVitalEntity));

    }

    @Override
    public List<SignoVital> getAllSignoVitales(){

        List<SignoVitalEntity> signoVitalEntities = signoVitalRepository.findAll();

        return signoVitalEntities.stream().
                map(signoVitalMapper::toSignoVital)
                .collect(Collectors.toList());


    }
}
