package webvet.v1.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.VeterinarioDto;
import webvet.v1.domain.aggregates.model.Veterinario;
import webvet.v1.infraestructure.entity.VeterinarioEntity;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VeterinarioMapper {


    @Mappings({

            @Mapping(source = "veterinarioId" , target = "veterinarioId"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),


    })
    Veterinario toVeterinario(VeterinarioEntity veterinarioEntity);

    @InheritInverseConfiguration
    VeterinarioEntity toVeterinarioEntity(Veterinario veterinario);

    @Mappings({

            @Mapping(source = "veterinarioId" , target = "veterinarioId"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),


    })
    VeterinarioDto toVeterinarioDto(Veterinario veterinario);

    @Mappings({

            @Mapping(source = "veterinarioId" , target = "veterinarioId"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),


    })
    Veterinario toVeterinarioFromDto(VeterinarioDto veterinarioDto);

    @Mappings({

            @Mapping(source = "veterinarioId" , target = "veterinarioId"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),


    })
    List<VeterinarioDto> toVeterinarioDto(List<Veterinario> veterinarios);

    @Mappings({

            @Mapping(source = "veterinarioId" , target = "veterinarioId"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),


    })
    VeterinarioDto toVeterinarioDtoFromEntity(VeterinarioEntity veterinarioEntity);


}
