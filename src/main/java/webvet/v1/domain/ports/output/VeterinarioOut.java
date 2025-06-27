package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Veterinario;
import webvet.v1.infraestructure.entity.VeterinarioEntity;

import java.util.List;
import java.util.Optional;

public interface VeterinarioOut {

    Optional<Veterinario> CreateVeterinario(Veterinario veterinario);

    List<Veterinario> getAllVeterinarios();

    Optional<VeterinarioEntity> getVeterinarioByApellido(String apellido);


}
