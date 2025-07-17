package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.EspecieDto;
import webvet.v1.domain.aggregates.model.Especie;
import webvet.v1.domain.ports.input.EspecieIn;
import webvet.v1.domain.ports.output.EspecieOut;
import webvet.v1.infraestructure.mapper.EspecieMapper;
import webvet.v1.infraestructure.repository.EspecieRepository;

import java.util.List;
import java.util.Optional;


@Service
public class EspecieUseCase implements EspecieIn {

    private final EspecieOut especieOut;
    private final EspecieRepository especieRepository;
    private final EspecieMapper especieMapper;


    public EspecieUseCase(EspecieOut especieOut, EspecieRepository especieRepository, EspecieMapper especieMapper) {
        this.especieOut = especieOut;
        this.especieRepository = especieRepository;
        this.especieMapper = especieMapper;
    }

    @Override
    public Optional<EspecieDto> createEspecie(EspecieDto especieDto) {

        Especie especie = especieMapper.toEspecieFromDto(especieDto);

        Optional<Especie> especieCreada = especieOut.createEspecie(especie);

        return especieCreada.map(especieMapper::toEspecieDto);

    }


    @Override
    public List<EspecieDto> getAllEspecies(){
        List<Especie> especies = especieOut.getAllEspecies();

        if (especies.isEmpty()){
            throw new EntityNotFoundException("No se encontraron especies registradas");
        }

        return especieMapper.toEspecieDto(especies);

    }

    @Override
    public boolean deleteEspecie(Long especieId){
        return especieOut.deleteEspecie(especieId);
    }
}
