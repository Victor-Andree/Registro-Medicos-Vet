package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.TipoServicioDto;
import webvet.v1.application.dto.TipoSignoVitalDto;
import webvet.v1.domain.aggregates.model.TipoServicio;
import webvet.v1.domain.aggregates.model.TipoSignoVital;
import webvet.v1.domain.ports.input.TipoServicioIn;
import webvet.v1.domain.ports.output.TipoServicioOut;
import webvet.v1.infraestructure.mapper.TipoServicioMapper;
import webvet.v1.infraestructure.repository.TipoServicioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioUseCase implements TipoServicioIn {

    private final TipoServicioOut tipoServicioOut;
    private final TipoServicioRepository tipoServicioRepository;
    private final TipoServicioMapper tipoServicioMapper;


    public TipoServicioUseCase(TipoServicioOut tipoServicioOut, TipoServicioRepository tipoServicioRepository, TipoServicioMapper tipoServicioMapper) {
        this.tipoServicioOut = tipoServicioOut;
        this.tipoServicioRepository = tipoServicioRepository;
        this.tipoServicioMapper = tipoServicioMapper;
    }

    @Override
    public Optional<TipoServicioDto> createTiposServicio(TipoServicioDto tipoServicioDto){

        TipoServicio tipoServicio = tipoServicioMapper.toTipoServicioFromDto(tipoServicioDto);
        Optional<TipoServicio> tipoServicioCreado =tipoServicioOut.createTiposServicio(tipoServicio);

        return tipoServicioCreado.map(tipoServicioMapper::toTipoServicioDto);
    }

    @Override
    public List<TipoServicioDto> getAllTiposServicios(){

        List<TipoServicio> tipoServicios = tipoServicioOut.getAllTiposServicios();

        if (tipoServicios.isEmpty()){
            throw new EntityNotFoundException("No TipossignosVitales encontrados");
        }

        return tipoServicioMapper.toTipoServicioDto(tipoServicios);

    }
}
