package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.PerfilPersonal;
import webvet.v1.domain.ports.output.PerfilPersonalOut;
import webvet.v1.infraestructure.entity.PerfilPersonalEntity;
import webvet.v1.infraestructure.mapper.PerfilPersonalMapper;
import webvet.v1.infraestructure.repository.PerfilPersonalRepository;
import webvet.v1.infraestructure.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfilPersonalAdapter implements PerfilPersonalOut {

    private final PerfilPersonalRepository perfilPersonalRepository;
    private final PerfilPersonalMapper perfilPersonalMapper;
    private final UsuarioRepository usuarioRepository;


    public PerfilPersonalAdapter(PerfilPersonalRepository perfilPersonalRepository, PerfilPersonalMapper perfilPersonalMapper, UsuarioRepository usuarioRepository) {
        this.perfilPersonalRepository = perfilPersonalRepository;
        this.perfilPersonalMapper = perfilPersonalMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<PerfilPersonal> createPerfil(PerfilPersonal perfil) {
        PerfilPersonalEntity entity = perfilPersonalMapper.toPefilPersonalEntity(perfil);

        usuarioRepository.findById(perfil.getUsuarioId())
                .ifPresent(entity::setUsuario);

        PerfilPersonalEntity saved = perfilPersonalRepository.save(entity);
        return Optional.of(perfilPersonalMapper.toPerfilPersonal(saved));
    }

    @Override
    public Optional<PerfilPersonal> updatePefil(PerfilPersonal perfil) {
        Optional<PerfilPersonalEntity> optionalEntity = perfilPersonalRepository.findById(perfil.getPerfilId());

        if (optionalEntity.isEmpty()) return Optional.empty();

        PerfilPersonalEntity entity = optionalEntity.get();
        entity.setNombres(perfil.getNombres());
        entity.setApellidos(perfil.getApellidos());
        entity.setTelefonoEmergencia(perfil.getTelefonoEmergencia());
        entity.setDireccion(perfil.getDireccion());
        entity.setAlergias(perfil.getAlergias());

        PerfilPersonalEntity updated = perfilPersonalRepository.save(entity);
        return Optional.of(perfilPersonalMapper.toPerfilPersonal(updated));
    }

    @Override
    public Optional<PerfilPersonal> getPefilByUsuarioId(int usuarioId) {
        return perfilPersonalRepository.findByUsuario_Id(usuarioId)
                .map(perfilPersonalMapper::toPerfilPersonal);
    }

    @Override
    public List<PerfilPersonal> getAllUsuarios() {
        return perfilPersonalRepository.findAll().stream()
                .map(perfilPersonalMapper::toPerfilPersonal)
                .collect(Collectors.toList());
    }

}
