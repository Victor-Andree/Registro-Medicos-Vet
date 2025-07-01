package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.TipoServicioDto;
import webvet.v1.application.dto.TipoVisitaDto;
import webvet.v1.domain.aggregates.model.TipoServicio;
import webvet.v1.domain.aggregates.model.TipoVisita;
import webvet.v1.domain.ports.input.TipoVisitaIn;
import webvet.v1.domain.ports.output.TipoVisitaOut;
import webvet.v1.infraestructure.mapper.TipoVisitaMapper;
import webvet.v1.infraestructure.repository.TipoVisitaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TipoVisitaUseCase implements TipoVisitaIn {

    private final TipoVisitaOut tipoVisitaOut;
    private final TipoVisitaMapper tipoVisitaMapper;
    private  final TipoVisitaRepository tipoVisitaRepository;


    public TipoVisitaUseCase(TipoVisitaOut tipoVisitaOut, TipoVisitaMapper tipoVisitaMapper, TipoVisitaRepository tipoVisitaRepository) {
        this.tipoVisitaOut = tipoVisitaOut;
        this.tipoVisitaMapper = tipoVisitaMapper;
        this.tipoVisitaRepository = tipoVisitaRepository;
    }

    @Override
    public  Optional<TipoVisitaDto> createTipoVisita(TipoVisitaDto tipoVisitaDto){

        TipoVisita tipoVisita = tipoVisitaMapper.toTipoVisitaFromDto(tipoVisitaDto);
        Optional<TipoVisita> tipoVisitaCreado =tipoVisitaOut.createTipoVisita(tipoVisita);

        return tipoVisitaCreado.map(tipoVisitaMapper::toTipoVistaDto);
    }

    @Override
    public List<TipoVisitaDto> getAllTipoVisitas(){

        List<TipoVisita> AlltipoVisitas = tipoVisitaOut.getAllTipoVisitas();

        if (AlltipoVisitas.isEmpty()){
            throw new EntityNotFoundException("No TipossignosVitales encontrados");
        }

        return tipoVisitaMapper.toTipoVistaDto(AlltipoVisitas);

    }

}
