package webvet.v1.infraestructure.adapters;


import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Veterinario;
import webvet.v1.domain.ports.output.VeterinarioOut;
import webvet.v1.infraestructure.entity.RazaEntity;
import webvet.v1.infraestructure.entity.VeterinarioEntity;
import webvet.v1.infraestructure.mapper.VeterinarioMapper;
import webvet.v1.infraestructure.repository.VeterinarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeterinarioAdapter implements VeterinarioOut {

    private final VeterinarioMapper veterinarioMapper;
    private final VeterinarioRepository veterinarioRepository;


    public VeterinarioAdapter(VeterinarioMapper veterinarioMapper, VeterinarioRepository veterinarioRepository) {
        this.veterinarioMapper = veterinarioMapper;
        this.veterinarioRepository = veterinarioRepository;
    }


    @Override
    public Optional<Veterinario> CreateVeterinario(Veterinario veterinario){
        VeterinarioEntity veterinarioEntity = veterinarioMapper.toVeterinarioEntity(veterinario);
        veterinarioRepository.save(veterinarioEntity);

        return Optional.of(veterinarioMapper.toVeterinario(veterinarioEntity));

    }

    @Override
    public List<Veterinario> getAllVeterinarios(){
        List<VeterinarioEntity> veterinarioEntities = veterinarioRepository.findAll();

        return veterinarioEntities.stream()
                .map(veterinarioMapper::toVeterinario)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VeterinarioEntity> getVeterinarioByApellido(String apellido){

        return veterinarioRepository.findByApellido(apellido);

    }

    @Override
    public Optional<Veterinario> updateDni (Long veterinarioId, String dni){
        Optional<VeterinarioEntity> optionalVet = veterinarioRepository.findById(veterinarioId);
        if (optionalVet.isEmpty()) return Optional.empty();

        VeterinarioEntity entity = optionalVet.get();
        entity.setDni(dni);

        VeterinarioEntity updated = veterinarioRepository.save(entity);
        return Optional.of(veterinarioMapper.toVeterinario(updated));

    }


}
