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
            @Mapping(source = "estado" , target = "estado"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
    })

    Usuario toUsuario(UsuarioEntity usuarioEntity);

    @InheritInverseConfiguration
    UsuarioEntity toUsuarioEntity(Usuario usuario);

    @Mappings({
            @Mapping(source = "usuarioId" , target = "usuarioId"),
            @Mapping(source = "username" , target = "username"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "rol" , target = "rol"),
            @Mapping(source = "estado" , target = "estado"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
    })
    UsuarioDto toUsuarioDto(Usuario usuario);

    @Mappings({
            @Mapping(source = "usuarioId" , target = "usuarioId"),
            @Mapping(source = "username" , target = "username"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "rol" , target = "rol"),
            @Mapping(source = "estado" , target = "estado"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
    })
    Usuario toUsuarioFromtDto(UsuarioDto usuarioDto);

    @Mappings({
            @Mapping(source = "usuarioId" , target = "usuarioId"),
            @Mapping(source = "username" , target = "username"),
            @Mapping(source = "password" , target = "password"),
            @Mapping(source = "rol" , target = "rol"),
            @Mapping(source = "estado" , target = "estado"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
    })
    List<UsuarioDto> toUsuarioDto(List<Usuario> usuarios);



}
