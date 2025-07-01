package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.TipoVisitaDto;
import webvet.v1.domain.aggregates.model.TipoVisita;
import webvet.v1.infraestructure.entity.TipoVisitaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoVisitaMapper {

    @Mappings({
            @Mapping(target = "tipoVisitaId", source = "tipoVisitaId"),
            @Mapping(target = "nombre", source = "nombre"),
    })
    TipoVisita toTipoVisita(TipoVisitaEntity tipoVisitaEntity);

    @InheritInverseConfiguration
    TipoVisitaEntity toTipoVisitaEntity(TipoVisita tipoVisita);


    @Mappings({
            @Mapping(target = "tipoVisitaId", source = "tipoVisitaId"),
            @Mapping(target = "nombre", source = "nombre"),
    })
    TipoVisitaDto toTipoVistaDto(TipoVisita tipoVisita);

    @Mappings({
            @Mapping(target = "tipoVisitaId", source = "tipoVisitaId"),
            @Mapping(target = "nombre", source = "nombre"),
    })
    TipoVisita toTipoVisitaFromDto(TipoVisitaDto tipoVisitaDto);

    @Mappings({
            @Mapping(target = "tipoVisitaId", source = "tipoVisitaId"),
            @Mapping(target = "nombre", source = "nombre"),
    })
    List<TipoVisitaDto> toTipoVistaDto(List<TipoVisita> tipoVisita);

    @Mappings({
            @Mapping(target = "tipoVisitaId", source = "tipoVisitaId"),
            @Mapping(target = "nombre", source = "nombre"),
    })
    TipoVisitaDto toTipoVistaDtoFromEntity(TipoVisitaEntity tipoVisitaEntity);





}
