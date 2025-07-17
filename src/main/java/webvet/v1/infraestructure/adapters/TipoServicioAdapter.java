package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.TipoServicio;
import webvet.v1.domain.ports.output.TipoServicioOut;
import webvet.v1.infraestructure.entity.TipoServicioEntity;
import webvet.v1.infraestructure.mapper.TipoServicioMapper;
import webvet.v1.infraestructure.repository.TipoServicioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoServicioAdapter implements TipoServicioOut {

    private final TipoServicioMapper tipoServicioMapper;
    private final TipoServicioRepository tipoServicioRepository;

    public TipoServicioAdapter(TipoServicioMapper tipoServicioMapper, TipoServicioRepository tipoServicioRepository) {
        this.tipoServicioMapper = tipoServicioMapper;
        this.tipoServicioRepository = tipoServicioRepository;
    }

    @Override
    public  Optional<TipoServicio> createTiposServicio(TipoServicio tipoServicio){

        TipoServicioEntity tipoServicioEntity = tipoServicioMapper.toTipoServicioEntity(tipoServicio);
        tipoServicioRepository.save(tipoServicioEntity);

        return Optional.of(tipoServicioMapper.toTipoServicio(tipoServicioEntity));


    }

    @Override
    public   List<TipoServicio> getAllTiposServicios(){

        List<TipoServicioEntity> tipoServicioEntities = tipoServicioRepository.findAll();

        return tipoServicioEntities.stream().
                map(tipoServicioMapper::toTipoServicio)
                .collect(Collectors.toList());


    }

    @Override
    public boolean deleteTipoServicio(Long tipoServicioId){
        if (tipoServicioRepository.existsById(tipoServicioId)){
            tipoServicioRepository.deleteById(tipoServicioId);
        }

        return false;
    }
}
