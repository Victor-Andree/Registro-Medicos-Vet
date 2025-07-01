package webvet.v1.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.TipoSignoVitalDto;
import webvet.v1.domain.aggregates.model.TipoSignoVital;
import webvet.v1.infraestructure.entity.TipoSignoVitalEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoSignoVitalMapper {


    @Mappings({

            @Mapping(source = "tipoSignoVitalId", target ="tipoSignoVitalId"),
            @Mapping(source = "nombre", target ="nombre"),

    })
    TipoSignoVital toTipoSignoVital(TipoSignoVitalEntity tipoSignoVitalEntity);

    @InheritInverseConfiguration
    TipoSignoVitalEntity toTipoSignoVitalEntity(TipoSignoVital tipoSignoVital);

    @Mappings({

            @Mapping(source = "tipoSignoVitalId", target ="tipoSignoVitalId"),
            @Mapping(source = "nombre", target ="nombre"),

    })
    TipoSignoVitalDto toTipoSignoVitalDto(TipoSignoVital tipoSignoVital);

    @Mappings({

            @Mapping(source = "tipoSignoVitalId", target ="tipoSignoVitalId"),
            @Mapping(source = "nombre", target ="nombre"),

    })
    TipoSignoVital toSignoVitalFromDto(TipoSignoVitalDto tipoSignoVitalDto);

    @Mappings({

            @Mapping(source = "tipoSignoVitalId", target ="tipoSignoVitalId"),
            @Mapping(source = "nombre", target ="nombre"),

    })
    List<TipoSignoVitalDto> toSignoVitalDto(List<TipoSignoVital> tipoSignoVital);

    @Mappings({

            @Mapping(source = "tipoSignoVitalId", target ="tipoSignoVitalId"),
            @Mapping(source = "nombre", target ="nombre"),

    })

    TipoSignoVitalDto toSignoVitalDtoFromEntity(TipoSignoVitalEntity tipoSignoVitalEntity);






}
