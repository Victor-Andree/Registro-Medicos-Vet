package webvet.v1.application.useCase;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.PerfilPersonalDto;
import webvet.v1.domain.aggregates.model.PerfilPersonal;
import webvet.v1.domain.ports.input.PerfilPersonalIn;
import webvet.v1.domain.ports.output.PerfilPersonalOut;
import webvet.v1.infraestructure.mapper.PerfilPersonalMapper;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilPersonalUseCase implements PerfilPersonalIn {

    private final PerfilPersonalOut perfilPersonalOut;
    private final PerfilPersonalMapper perfilPersonalMapper;


    public PerfilPersonalUseCase(PerfilPersonalOut perfilPersonalOut, PerfilPersonalMapper perfilPersonalMapper) {
        this.perfilPersonalOut = perfilPersonalOut;
        this.perfilPersonalMapper = perfilPersonalMapper;
    }

    @Override
    public Optional<PerfilPersonalDto> createPerfil(PerfilPersonalDto dto) {
        PerfilPersonal perfil = perfilPersonalMapper.toPerfilPersonalFromDto(dto);
        return perfilPersonalOut.createPerfil(perfil).map(perfilPersonalMapper::toPerfilPersonalDto);
    }

    @Override
    public Optional<PerfilPersonalDto> updatePerfil(PerfilPersonalDto dto) {
        PerfilPersonal perfil = perfilPersonalMapper.toPerfilPersonalFromDto(dto);
        return perfilPersonalOut.updatePefil(perfil).map(perfilPersonalMapper::toPerfilPersonalDto);
    }

    @Override
    public Optional<PerfilPersonalDto> getPerfilByUsuarioId(int usuarioId) {
        return perfilPersonalOut.getPefilByUsuarioId(usuarioId).map(perfilPersonalMapper::toPerfilPersonalDto);
    }

    @Override
    public List<PerfilPersonalDto> getAllUsuarios() {
        return perfilPersonalMapper.toPerfilPersonalFromDto(perfilPersonalOut.getAllUsuarios());
    }

}
