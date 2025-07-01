package webvet.v1.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.SignoVitalDto;
import webvet.v1.domain.aggregates.model.SignoVital;
import webvet.v1.infraestructure.entity.SignoVitalEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SignoVitalMapper {

    @Mappings({
            @Mapping(source = "signoVitalId", target = "signoVitalId"),
            @Mapping(source = "tipoSignoVital", target = "tipoSignoVital"),
            @Mapping(source = "valor", target = "valor"),
            

    })
    SignoVital toSignoVital(SignoVitalEntity signoVitalEntity);

    @InheritInverseConfiguration
    SignoVitalEntity toSignoVitalEntity(SignoVital signoVital);

    @Mappings({
            @Mapping(source = "signoVitalId", target = "signoVitalId"),
            @Mapping(source = "tipoSignoVital.tipoSignoVitalId", target = "tipoSignoVitalId"),
            @Mapping(source = "valor", target = "valor"),

    })
    SignoVitalDto toSignoVitalDto(SignoVital signoVital);

    @Mappings({
            @Mapping(source = "signoVitalId", target = "signoVitalId"),
            @Mapping(source = "tipoSignoVitalId", target = "tipoSignoVital.tipoSignoVitalId"),
            @Mapping(source = "valor", target = "valor"),

    })
    SignoVital toSignoVitalFromDto(SignoVitalDto signoVitalDto);

    @Mappings({
            @Mapping(source = "signoVitalId", target = "signoVitalId"),
            @Mapping(source = "tipoSignoVital", target = "tipoSignoVital"),
            @Mapping(source = "valor", target = "valor"),

    })
    List<SignoVitalDto> toSignoVitalDto(List<SignoVital> signoVital);

    @Mappings({
            @Mapping(source = "signoVitalId", target = "signoVitalId"),
            @Mapping(source = "tipoSignoVital.tipoSignoVitalId", target = "tipoSignoVitalId"),
            @Mapping(source = "valor", target = "valor"),

    })

    SignoVitalDto toSignoVitalDtoFromEntity(SignoVitalEntity signoVitalEntity);

}
