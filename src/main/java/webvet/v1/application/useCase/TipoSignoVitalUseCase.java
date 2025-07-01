package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.TipoSignoVitalDto;
import webvet.v1.domain.aggregates.model.SignoVital;
import webvet.v1.domain.aggregates.model.TipoSignoVital;
import webvet.v1.domain.ports.input.TipoSignoVitalIn;
import webvet.v1.domain.ports.output.TipoSignoVitalOut;
import webvet.v1.infraestructure.mapper.TipoSignoVitalMapper;
import webvet.v1.infraestructure.repository.TipoSignoVitalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TipoSignoVitalUseCase implements TipoSignoVitalIn {

    private final TipoSignoVitalOut tipoSignoVitalOut;
    private final TipoSignoVitalRepository tipoSignoVitalRepository;
    private final TipoSignoVitalMapper tipoSignoVitalMapper;

    public TipoSignoVitalUseCase(TipoSignoVitalOut tipoSignoVitalOut, TipoSignoVitalRepository tipoSignoVitalRepository, TipoSignoVitalMapper tipoSignoVitalMapper) {
        this.tipoSignoVitalOut = tipoSignoVitalOut;
        this.tipoSignoVitalRepository = tipoSignoVitalRepository;
        this.tipoSignoVitalMapper = tipoSignoVitalMapper;
    }

    @Override
    public Optional<TipoSignoVitalDto> createTipoSignoVital(TipoSignoVitalDto tipoSignoVitalDto){

        TipoSignoVital tipoSignoVital = tipoSignoVitalMapper.toSignoVitalFromDto(tipoSignoVitalDto);
        Optional<TipoSignoVital> TiposignoVitalCreado =tipoSignoVitalOut.createTipoSignoVital(tipoSignoVital);

        return TiposignoVitalCreado.map(tipoSignoVitalMapper::toTipoSignoVitalDto);
    }

    @Override
    public List<TipoSignoVitalDto> getAllTiposSignoVitales(){

        List<TipoSignoVital> TiposignoVitals = tipoSignoVitalOut.getAllTiposSignoVitales();

        if (TiposignoVitals.isEmpty()){
            throw new EntityNotFoundException("No TipossignosVitales encontrados");
        }

        return tipoSignoVitalMapper.toSignoVitalDto(TiposignoVitals);

    }
}
