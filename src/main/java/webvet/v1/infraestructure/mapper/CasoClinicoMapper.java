package webvet.v1.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.CasoClinicoDto;
import webvet.v1.domain.aggregates.model.CasoClinico;
import webvet.v1.infraestructure.entity.CasoClinicoEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CasoClinicoMapper {

    @Mappings({

            @Mapping(source = "casoClinicoId", target = "casoClinicoId" ),
            @Mapping(source = "descripcion" , target ="descripcion"),
            @Mapping(source ="mascota" , target="mascota" )
    })
    CasoClinico toCasoClinico(CasoClinicoEntity casoClinicoEntity);

    @InheritInverseConfiguration
    CasoClinicoEntity toCasoClinicoEntity(CasoClinico casoClinico);

    @Mappings({

            @Mapping(source = "casoClinicoId", target = "casoClinicoId" ),
            @Mapping(source = "descripcion" , target ="descripcion"),
            @Mapping(source ="mascota.mascotaId" , target="mascotaId" )
    })
    CasoClinicoDto toCasoClinicoDto(CasoClinico casoClinico);

    @Mappings({

            @Mapping(source = "casoClinicoId", target = "casoClinicoId" ),
            @Mapping(source = "descripcion" , target ="descripcion"),
            @Mapping(source ="mascotaId" , target="mascota.mascotaId" )
    })
    CasoClinico toCasoClinicoFromDto(CasoClinicoDto casoClinicoDto);

    @Mappings({

            @Mapping(source = "casoClinicoId", target = "casoClinicoId" ),
            @Mapping(source = "descripcion" , target ="descripcion"),
            @Mapping(source ="mascota.mascotaId" , target="mascota" )
    })
    List<CasoClinicoDto> toCasoClinicoDto(List<CasoClinico> casoClinicos);

    @Mappings({

            @Mapping(source = "casoClinicoId", target = "casoClinicoId" ),
            @Mapping(source = "descripcion" , target ="descripcion"),
            @Mapping(source ="mascota.mascotaId" , target="mascotaId" )
    })
    CasoClinicoDto toMCasoClinicoDtoFromEntity(CasoClinicoEntity casoClinicoEntity);


}
