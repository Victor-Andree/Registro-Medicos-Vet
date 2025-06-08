package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.UsuarioDto;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.infraestructure.entity.UsuarioEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mappings({
            @Mapping(source = "id" , target = "usuarioId"),
            @Mapping(source = "username" , target = "username"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "rol" , target = "rol"),
    })

    Usuario toUsuario(UsuarioEntity usuarioEntity);

    @InheritInverseConfiguration
    UsuarioEntity toUsuarioEntity(Usuario usuario);

    @Mappings({
            @Mapping(source = "usuarioId" , target = "usuarioId"),
            @Mapping(source = "username" , target = "username"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "rol" , target = "rol"),
    })
    UsuarioDto toUsuarioDto(Usuario usuario);

    @Mappings({
            @Mapping(source = "usuarioId" , target = "usuarioId"),
            @Mapping(source = "username" , target = "username"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "rol" , target = "rol"),
    })
    Usuario toUsuarioFromtDto(UsuarioDto usuarioDto);

    @Mappings({
            @Mapping(source = "usuarioId" , target = "usuarioId"),
            @Mapping(source = "username" , target = "username"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "rol" , target = "rol"),
    })
    List<UsuarioDto> toUsuarioDto(List<Usuario> usuarios);



}
