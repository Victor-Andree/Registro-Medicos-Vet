package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.EspecieDto;
import webvet.v1.domain.aggregates.model.Especie;
import webvet.v1.infraestructure.entity.EspecieEntity;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EspecieMapper {

    @Mappings({
            @Mapping(source = "especieId", target = "especieId"),
            @Mapping(source = "nombre", target = "nombre"),
    })

    EspecieDto toEspecieDto(Especie especie);

    @Mappings({
            @Mapping(source = "especieId", target = "especieId"),
            @Mapping(source = "nombre", target = "nombre"),
    })

    Especie toEspecieFromDto(EspecieDto especieDto);

    @Mappings({
            @Mapping(source = "especieId", target = "especieId"),
            @Mapping(source = "nombre", target = "nombre"),
    })

    List<EspecieDto> toEspecieDto( List<Especie> especie);

    @Mappings({
            @Mapping(source = "especieId", target = "especieId"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    Especie toEspecie(EspecieEntity especieEntity);

    @InheritInverseConfiguration
    EspecieEntity toEspecieEntity(Especie especie);





}
