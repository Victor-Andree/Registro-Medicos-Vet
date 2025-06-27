package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.TipoServicioDto;
import webvet.v1.domain.aggregates.model.TipoServicio;
import webvet.v1.infraestructure.entity.TipoServicioEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoServicioMapper {

    @Mappings({

            @Mapping(source = "tiposervicioId" , target = "tipoServicioId"),
            @Mapping(source = "nombre" , target = "nombre")

    })

    TipoServicio toTipoServicio(TipoServicioEntity tipoServicioEntity);

    @InheritInverseConfiguration
    TipoServicioEntity toTipoServicioEntity(TipoServicio tipoServicio);

    @Mappings({

            @Mapping(source = "tipoServicioId" , target = "tipoServicioId"),
            @Mapping(source = "nombre" , target = "nombre")

    })
    TipoServicioDto toTipoServicioDto(TipoServicio tipoServicio);

    @Mappings({

            @Mapping(source = "tipoServicioId" , target = "tipoServicioId"),
            @Mapping(source = "nombre" , target = "nombre")

    })
    TipoServicio toTipoServicioFromDto(TipoServicioDto tipoServicioDto);

    @Mappings({

            @Mapping(source = "tipoServicioId" , target = "tipoServicioId"),
            @Mapping(source = "nombre" , target = "nombre")

    })
    List<TipoServicioDto> toTipoServicioDto(List<TipoServicio> tipoServicios);

    @Mappings({

            @Mapping(source = "tiposervicioId" , target = "tipoServicioId"),
            @Mapping(source = "nombre" , target = "nombre")

    })
    TipoServicioDto toTipoServicioFromEntity(TipoServicioEntity tipoServicioEntity);

}
