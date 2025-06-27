package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.VeterinarioDto;
import webvet.v1.domain.aggregates.model.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioIn {


    Optional<VeterinarioDto> CreateVeterinario(VeterinarioDto veterinarioDto);

    List<VeterinarioDto> getAllVeterinarios();

    Optional<VeterinarioDto> getVeterinarioByApellido(String apellido);


}
