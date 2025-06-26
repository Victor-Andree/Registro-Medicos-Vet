package webvet.v1.application.useCase;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.EspecieDto;
import webvet.v1.application.dto.RazaDto;
import webvet.v1.domain.aggregates.model.Especie;
import webvet.v1.domain.aggregates.model.Raza;
import webvet.v1.domain.ports.input.RazaIn;
import webvet.v1.domain.ports.output.RazaOut;
import webvet.v1.infraestructure.mapper.RazaMapper;
import webvet.v1.infraestructure.repository.RazaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RazaUseCase implements RazaIn {

    private final RazaOut razaOut;
    private final RazaMapper razaMapper;
    private final RazaRepository razaRepository;

    public RazaUseCase(RazaOut razaOut, RazaMapper razaMapper, RazaRepository razaRepository) {
        this.razaOut = razaOut;
        this.razaMapper = razaMapper;
        this.razaRepository = razaRepository;
    }


    @Override
    public Optional<RazaDto> createRaza(RazaDto razaDto) {

        Raza raza = razaMapper.toRazafromDto(razaDto);

        Optional<Raza> razaCreada = razaOut.createRaza(raza);

        return razaCreada.map(razaMapper::toRazaDto);

    }


    @Override
    public List<RazaDto> getAllRazas(){
        List<Raza> razas = razaOut.getAllRazas();

        if (razas.isEmpty()){
            throw new EntityNotFoundException("No se encontraron razas registradas");
        }

        return razaMapper.toRazaDto(razas);

    }
}
