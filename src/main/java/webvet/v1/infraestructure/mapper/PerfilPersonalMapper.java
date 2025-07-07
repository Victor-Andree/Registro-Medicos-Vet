package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.PerfilPersonalDto;
import webvet.v1.domain.aggregates.model.PerfilPersonal;
import webvet.v1.infraestructure.entity.PerfilPersonalEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilPersonalMapper {

    @Mappings({
            @Mapping(source = "perfilId", target = "perfilId"),
            @Mapping(source = "nombres", target = "nombres"),
            @Mapping(source = "apellidos", target = "apellidos"),
            @Mapping(source = "telefonoEmergencia", target = "telefonoEmergencia"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "alergias", target = "alergias"),
            @Mapping(source = "usuarioId", target = "usuarioId"),


    })
    PerfilPersonalDto toPerfilPersonalDto(PerfilPersonal perfilPersonal);

    @Mappings({
            @Mapping(source = "perfilId", target = "perfilId"),
            @Mapping(source = "nombres", target = "nombres"),
            @Mapping(source = "apellidos", target = "apellidos"),
            @Mapping(source = "telefonoEmergencia", target = "telefonoEmergencia"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "alergias", target = "alergias"),
            @Mapping(source = "usuarioId", target = "usuarioId")


    })
    PerfilPersonal toPerfilPersonalFromDto(PerfilPersonalDto perfilPersonalDto);

    @Mappings({
            @Mapping(source = "perfilId", target = "perfilId"),
            @Mapping(source = "nombres", target = "nombres"),
            @Mapping(source = "apellidos", target = "apellidos"),
            @Mapping(source = "telefonoEmergencia", target = "telefonoEmergencia"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "alergias", target = "alergias"),
            @Mapping(source = "usuarioId", target = "usuario"),



    })

    List<PerfilPersonalDto> toPerfilPersonalFromDto(List<PerfilPersonal> perfilPersonal);


    @Mappings({
            @Mapping(source = "perfilId", target = "perfilId"),
            @Mapping(source = "nombres", target = "nombres"),
            @Mapping(source = "apellidos", target = "apellidos"),
            @Mapping(source = "telefonoEmergencia", target = "telefonoEmergencia"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "alergias", target = "alergias"),
            @Mapping(source = "usuario.id", target = "usuarioId"),


    })

    PerfilPersonal toPerfilPersonal(PerfilPersonalEntity perfilPersonalEntity);

    @InheritInverseConfiguration
    PerfilPersonalEntity toPefilPersonalEntity(PerfilPersonal perfilPersonal);





}
