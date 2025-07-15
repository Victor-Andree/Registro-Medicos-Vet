package webvet.v1.infraestructure.adapters;


import org.springframework.stereotype.Service;
import webvet.v1.application.dto.VisitaDto;
import webvet.v1.domain.aggregates.model.Visita;
import webvet.v1.domain.ports.output.VisitaOut;
import webvet.v1.infraestructure.entity.CitaEntity;
import webvet.v1.infraestructure.entity.VisitaEntity;
import webvet.v1.infraestructure.mapper.VisitaMapper;
import webvet.v1.infraestructure.repository.VisitaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitaAdapter implements VisitaOut {

    private final VisitaMapper visitaMapper;
    private final VisitaRepository visitaRepository;


    public VisitaAdapter(VisitaMapper visitaMapper, VisitaRepository visitaRepository) {
        this.visitaMapper = visitaMapper;
        this.visitaRepository = visitaRepository;
    }


    @Override
    public Optional<Visita> createVisita(Visita visita){

        VisitaEntity visitaEntity = visitaMapper.toVisitaEntity(visita);
        visitaRepository.save(visitaEntity);

        return Optional.of(visitaMapper.toVisita(visitaEntity));

    }

    @Override
    public List<Visita> getAllVisitas(){

        List<VisitaEntity> visitaEntities = visitaRepository.findAll();

        return visitaEntities.stream().
                map(visitaMapper::toVisita)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Visita> foundVisitaById(Long visitaId){
        return visitaRepository.findById(visitaId)
                .map(visitaMapper::toVisita);
    }

    @Override
    public List<Visita> foundVisitaByTipoVisitaId(Long tipoVisitaId){
        List<VisitaEntity> visitaEntities = visitaRepository.findByTipoVisita_tipoVisitaId(tipoVisitaId);
        return visitaEntities.stream()
                .map(visitaMapper::toVisita)
                .collect(Collectors.toList());
    }




}
