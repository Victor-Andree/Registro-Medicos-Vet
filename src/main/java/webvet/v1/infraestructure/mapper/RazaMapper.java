package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.RazaDto;
import webvet.v1.domain.aggregates.model.Raza;
import webvet.v1.infraestructure.entity.RazaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RazaMapper {


    @Mappings({
            @Mapping(source = "razaId", target = "razaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "especieId" , target = "especieId")
    })

    RazaDto toRazaDto(Raza raza);

    @Mappings({
            @Mapping(source = "razaId", target = "razaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "especieId" , target = "especieId")
    })

    Raza toRazafromDto(RazaDto razaDto);

    @Mappings({
            @Mapping(source = "razaId", target = "razaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "especie.especieId" , target = "especieId")
    })

    List<RazaDto> toRazaDto(List<Raza> razas);

    @Mappings({
            @Mapping(source = "razaId", target = "razaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "especie.especieId" , target = "especieId")
    })

    Raza toRaza(RazaEntity razaEntity);

    @InheritInverseConfiguration
    RazaEntity toRazaEntity(Raza raza);



}
