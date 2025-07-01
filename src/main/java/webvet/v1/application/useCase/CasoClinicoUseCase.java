package webvet.v1.application.useCase;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.CasoClinicoDto;
import webvet.v1.domain.aggregates.model.CasoClinico;
import webvet.v1.domain.ports.input.CasoClinicoIn;
import webvet.v1.domain.ports.output.CasoClinicoOut;
import webvet.v1.infraestructure.mapper.CasoClinicoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CasoClinicoUseCase implements CasoClinicoIn {

    private final CasoClinicoOut casoClinicoOut;
    private final CasoClinicoMapper casoClinicoMapper;


    public CasoClinicoUseCase(CasoClinicoOut casoClinicoOut, CasoClinicoMapper casoClinicoMapper) {
        this.casoClinicoOut = casoClinicoOut;
        this.casoClinicoMapper = casoClinicoMapper;
    }

    @Override
    public Optional<CasoClinicoDto> createCasoClinico(CasoClinicoDto casoClinicoDto){
        CasoClinico casoClinico = casoClinicoMapper.toCasoClinicoFromDto(casoClinicoDto);

        Optional<CasoClinico> casoClinicoCreado = casoClinicoOut.createCasoClinico(casoClinico);

        return casoClinicoCreado.map(casoClinicoMapper::toCasoClinicoDto);
    }

    @Override
    public List<CasoClinicoDto> getAllCasoClinicos(){
        List<CasoClinico> casoClinicos = casoClinicoOut.getAllCasoClinicos();

        if (casoClinicos.isEmpty()){
            throw new EntityNotFoundException("No se encontraron casos clinicos registradas");
        }

        return casoClinicoMapper.toCasoClinicoDto(casoClinicos);
    }

    @Override
    public  Optional<CasoClinicoDto> updateCasoClinico(CasoClinicoDto casoClinicoDto){
        CasoClinico casoClinico = casoClinicoMapper.toCasoClinicoFromDto(casoClinicoDto);

        Optional<CasoClinico> casoClinicoActualizado = casoClinicoOut.updateCasoClinico(casoClinico);

        return casoClinicoActualizado.map(casoClinicoMapper::toCasoClinicoDto);
    }

    @Override
    public List<CasoClinicoDto> findByMascotaId(Long mascotaId){
        List<CasoClinico> casoClinicos = casoClinicoOut.findByMascotaId(mascotaId);
        return casoClinicos.stream()
                .map(casoClinicoMapper::toCasoClinicoDto)
                .collect(Collectors.toList());
    }


}
