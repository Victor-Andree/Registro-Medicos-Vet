package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.VisitaDto;
import webvet.v1.domain.aggregates.model.Visita;
import webvet.v1.domain.ports.input.VisitaIn;
import webvet.v1.domain.ports.output.VisitaOut;
import webvet.v1.infraestructure.mapper.VisitaMapper;
import webvet.v1.infraestructure.repository.VisitaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VisitaUseCase implements VisitaIn {

    private final VisitaOut visitaOut;
    private final VisitaRepository visitaRepository;
    private final VisitaMapper visitaMapper;


    public VisitaUseCase(VisitaOut visitaOut, VisitaRepository visitaRepository, VisitaMapper visitaMapper) {
        this.visitaOut = visitaOut;
        this.visitaRepository = visitaRepository;
        this.visitaMapper = visitaMapper;
    }

    @Override
    public Optional<VisitaDto> createVisita(VisitaDto visitaDto){

        Visita visita = visitaMapper.toVisitaFromDto(visitaDto);
        Optional<Visita> visitaCreada =visitaOut.createVisita(visita);

        return visitaCreada.map(visitaMapper::toVisitaDto);


    }

    @Override
    public List<VisitaDto> getAllVisitas(){

        List<Visita> visitas = visitaOut.getAllVisitas();

        if (visitas.isEmpty()){
            throw new EntityNotFoundException("No visita encontrada");
        }

        return visitaMapper.toVisitaDto(visitas);

    }
}
