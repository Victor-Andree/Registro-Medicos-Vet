package webvet.v1.infraestructure.adapters;


import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.TipoSignoVital;
import webvet.v1.domain.ports.output.TipoSignoVitalOut;
import webvet.v1.infraestructure.entity.TipoSignoVitalEntity;
import webvet.v1.infraestructure.mapper.TipoSignoVitalMapper;
import webvet.v1.infraestructure.repository.TipoSignoVitalRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoSignoVitalAdapter implements TipoSignoVitalOut {

    private final TipoSignoVitalRepository tipoSignoVitalRepository;
    private final TipoSignoVitalMapper tipoSignoVitalMapper;


    public TipoSignoVitalAdapter(TipoSignoVitalRepository tipoSignoVitalRepository, TipoSignoVitalMapper tipoSignoVitalMapper) {
        this.tipoSignoVitalRepository = tipoSignoVitalRepository;
        this.tipoSignoVitalMapper = tipoSignoVitalMapper;
    }

    @Override
    public Optional<TipoSignoVital> createTipoSignoVital(TipoSignoVital tipoSignoVital){

        TipoSignoVitalEntity tipoSignoVitalEntity = tipoSignoVitalMapper.toTipoSignoVitalEntity(tipoSignoVital);
        tipoSignoVitalRepository.save(tipoSignoVitalEntity);

        return Optional.of(tipoSignoVitalMapper.toTipoSignoVital(tipoSignoVitalEntity));


    }

    @Override
    public List<TipoSignoVital> getAllTiposSignoVitales(){

        List<TipoSignoVitalEntity> tipoSignoVitalEntities = tipoSignoVitalRepository.findAll();

        return tipoSignoVitalEntities.stream().
                map(tipoSignoVitalMapper::toTipoSignoVital)
                .collect(Collectors.toList());


    }
}
