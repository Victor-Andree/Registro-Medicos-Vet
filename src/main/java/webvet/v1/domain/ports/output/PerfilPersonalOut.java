package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.PerfilPersonal;

import java.util.List;
import java.util.Optional;

public interface PerfilPersonalOut {

    Optional<PerfilPersonal> createPerfil(PerfilPersonal perfilPersonal);

    Optional<PerfilPersonal> updatePefil(PerfilPersonal perfilPersonal);

    Optional<PerfilPersonal> getPefilByUsuarioId(int usuarioId);

    List<PerfilPersonal> getAllUsuarios();

}
