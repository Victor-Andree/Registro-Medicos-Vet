package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.VeterinarioDto;
import webvet.v1.domain.aggregates.model.Cliente;
import webvet.v1.domain.aggregates.model.Veterinario;
import webvet.v1.domain.ports.input.VeterinarioIn;
import webvet.v1.domain.ports.output.VeterinarioOut;
import webvet.v1.infraestructure.mapper.VeterinarioMapper;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioUseCase implements VeterinarioIn {

    private final VeterinarioOut veterinarioOut;

    private final VeterinarioMapper veterinarioMapper;


    public VeterinarioUseCase(VeterinarioOut veterinarioOut, VeterinarioMapper veterinarioMapper) {
        this.veterinarioOut = veterinarioOut;
        this.veterinarioMapper = veterinarioMapper;
    }

    @Override
    public Optional<VeterinarioDto> CreateVeterinario(VeterinarioDto veterinarioDto){
        Veterinario veterinario = veterinarioMapper.toVeterinarioFromDto(veterinarioDto);

        Optional<Veterinario> vetCreado = veterinarioOut.CreateVeterinario(veterinario);

        return vetCreado.map(veterinarioMapper::toVeterinarioDto);

    }

    @Override
    public List<VeterinarioDto> getAllVeterinarios(){
        List<Veterinario>veterinarios = veterinarioOut.getAllVeterinarios();

        if(veterinarios.isEmpty()){
            throw new EntityNotFoundException("No se encontro el veterinario");
        }

        return veterinarioMapper.toVeterinarioDto(veterinarios);
    }

    @Override
    public Optional<VeterinarioDto> getVeterinarioByApellido(String apellido){
        return veterinarioOut.getVeterinarioByApellido(apellido)
                .map(veterinarioMapper::toVeterinarioDtoFromEntity);
    }



}
