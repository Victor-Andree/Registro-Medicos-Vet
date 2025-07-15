package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.VisitaDto;
import webvet.v1.domain.aggregates.model.Visita;
import webvet.v1.infraestructure.entity.VisitaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitaMapper {


    @Mappings({

            @Mapping(source = "visitaId" , target = "visitaId"),
            @Mapping(source = "casoClinico" , target = "casoClinico"),
            @Mapping(source = "signoVital" , target = "signoVital"),
            @Mapping(source = "tipoVisita" , target = "tipoVisita"),

    })
    Visita toVisita(VisitaEntity visitaEntity);

    @InheritInverseConfiguration
    VisitaEntity toVisitaEntity(Visita visita);

    @Mappings({

            @Mapping(source = "visitaId" , target = "visitaId"),
            @Mapping(source = "casoClinico.casoClinicoId" , target = "casoClinicoId"),
            @Mapping(source = "signoVital" , target = "signoVital"),
            @Mapping(source = "tipoVisita.tipoVisitaId" , target = "tipoVisitaId"),

    })
    VisitaDto toVisitaDto(Visita visita);

    @Mappings({

            @Mapping(source = "visitaId" , target = "visitaId"),
            @Mapping(source = "casoClinicoId" , target = "casoClinico.casoClinicoId"),
            @Mapping(source = "signoVital" , target = "signoVital"),
            @Mapping(source = "tipoVisitaId" , target = "tipoVisita.tipoVisitaId"),

    })
    Visita toVisitaFromDto(VisitaDto visitaDto);

    @Mappings({

            @Mapping(source = "visitaId" , target = "visitaId"),
            @Mapping(source = "casoClinico.casoClinicoId" , target = "casoClinico"),
            @Mapping(source = "signoVital" , target = "signoVital"),
            @Mapping(source = "tipoVisita.tipoVisitaId" , target = "tipoVisita"),

    })
    List<VisitaDto> toVisitaDtoList(List<Visita> visitas);


    @Mappings({

            @Mapping(source = "visitaId" , target = "visitaId"),
            @Mapping(source = "casoClinico.casoClinicoId" , target = "casoClinicoId"),
            @Mapping(source = "signoVital" , target = "signoVital"),
            @Mapping(source = "tipoVisita.tipoVisitaId" , target = "tipoVisitaId"),

    })
    VisitaDto toVistaFromEntity(VisitaEntity visitaEntity);



}
