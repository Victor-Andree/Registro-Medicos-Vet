package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.CasoClinico;
import webvet.v1.domain.ports.output.CasoClinicoOut;
import webvet.v1.infraestructure.entity.CasoClinicoEntity;
import webvet.v1.infraestructure.mapper.CasoClinicoMapper;
import webvet.v1.infraestructure.mapper.MascotaMapper;
import webvet.v1.infraestructure.repository.CasoClinicoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CasoClinicoAdapter implements CasoClinicoOut {

    private final CasoClinicoMapper casoClinicoMapper;
    private final CasoClinicoRepository casoClinicoRepository;
    private final MascotaMapper mascotaMapper;


    public CasoClinicoAdapter(CasoClinicoMapper casoClinicoMapper, CasoClinicoRepository casoClinicoRepository, MascotaMapper mascotaMapper) {
        this.casoClinicoMapper = casoClinicoMapper;
        this.casoClinicoRepository = casoClinicoRepository;
        this.mascotaMapper = mascotaMapper;
    }

    @Override
    public Optional<CasoClinico> createCasoClinico(CasoClinico casoClinico){

        CasoClinicoEntity casoClinicoEntity = casoClinicoMapper.toCasoClinicoEntity(casoClinico);
        CasoClinicoEntity casoClinicoCreate = casoClinicoRepository.save(casoClinicoEntity);

        return Optional.of(casoClinicoMapper.toCasoClinico(casoClinicoCreate));
    }

    @Override
    public List<CasoClinico> getAllCasoClinicos(){
        List<CasoClinicoEntity> casoClinicoEntities = casoClinicoRepository.findAll();

        return casoClinicoEntities.stream()
                .map(casoClinicoMapper::toCasoClinico)
                .collect(Collectors.toList());
    }

    @Override
    public  Optional<CasoClinico> updateCasoClinico(CasoClinico casoClinico){
        Optional<CasoClinicoEntity> casoClinicoEntity = casoClinicoRepository.findById(casoClinico.getCasoClinicoId());

        if (casoClinicoEntity.isEmpty()) {
            return Optional.empty();
        }

        CasoClinicoEntity casoClinicoEntitys = casoClinicoEntity.get();

        // Actualizar campos
        casoClinicoEntitys.setDescripcion(casoClinico.getDescripcion());

        // Mapeos de objetos relacionados
        casoClinicoEntitys.setMascota(mascotaMapper.toMascotaEntity(casoClinico.getMascota()));

        // Guardar
        CasoClinicoEntity updated = casoClinicoRepository.save(casoClinicoEntitys);

        return Optional.of(casoClinicoMapper.toCasoClinico(updated));
    }

    @Override
    public  List<CasoClinico> findByMascotaId(Long mascotaId){
        List<CasoClinicoEntity> casoClinicoEntities = casoClinicoRepository.findByMascota_MascotaId(mascotaId);
        return casoClinicoEntities.stream()
                .map(casoClinicoMapper::toCasoClinico)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CasoClinico> findById(Long casoClinicoId){
        return casoClinicoRepository.findById(casoClinicoId)
                .map(casoClinicoMapper::toCasoClinico);

    }
}
