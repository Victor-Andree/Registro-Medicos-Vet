package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.TipoServicio;
import webvet.v1.domain.aggregates.model.TipoVisita;
import webvet.v1.domain.ports.output.TipoVisitaOut;
import webvet.v1.infraestructure.entity.TipoServicioEntity;
import webvet.v1.infraestructure.entity.TipoVisitaEntity;
import webvet.v1.infraestructure.mapper.TipoVisitaMapper;
import webvet.v1.infraestructure.repository.TipoVisitaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoVisitaAdapter implements TipoVisitaOut {

    private final TipoVisitaRepository tipoVisitaRepository;
    private final TipoVisitaMapper tipoVisitaMapper;


    public TipoVisitaAdapter(TipoVisitaRepository tipoVisitaRepository, TipoVisitaMapper tipoVisitaMapper) {
        this.tipoVisitaRepository = tipoVisitaRepository;
        this.tipoVisitaMapper = tipoVisitaMapper;
    }

    @Override
    public Optional<TipoVisita> createTipoVisita(TipoVisita tipoVisita){

        TipoVisitaEntity tipoVisitaEntity = tipoVisitaMapper.toTipoVisitaEntity(tipoVisita);
        tipoVisitaRepository.save(tipoVisitaEntity);

        return Optional.of(tipoVisitaMapper.toTipoVisita(tipoVisitaEntity));


    }

    @Override
    public List<TipoVisita> getAllTipoVisitas(){

        List<TipoVisitaEntity> tipoVisitaEntities = tipoVisitaRepository.findAll();

        return tipoVisitaEntities.stream().
                map(tipoVisitaMapper::toTipoVisita)
                .collect(Collectors.toList());


    }
}
