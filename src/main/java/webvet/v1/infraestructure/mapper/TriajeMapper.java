package webvet.v1.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.TriajeDto;
import webvet.v1.domain.aggregates.model.Triaje;
import webvet.v1.infraestructure.entity.TriajeEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TriajeMapper {

    @Mappings({
            @Mapping(source = "triajeId", target = "triajeId"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "temperatura", target = "temperatura"),
            @Mapping(source = "frecuenciaCardiaca", target = "frecuenciaCardiaca"),
            @Mapping(source = "frecuenciaRespiratoria", target = "frecuenciaRespiratoria"),
            @Mapping(source = "observaciones", target = "observaciones"),
            @Mapping(source = "mascotaId", target = "mascotaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "fechaActualizacion", target = "fechaActualizacion")


    })
    TriajeDto toTriajeDto(Triaje triaje);

    @Mappings({
            @Mapping(source = "triajeId", target = "triajeId"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "temperatura", target = "temperatura"),
            @Mapping(source = "frecuenciaCardiaca", target = "frecuenciaCardiaca"),
            @Mapping(source = "frecuenciaRespiratoria", target = "frecuenciaRespiratoria"),
            @Mapping(source = "observaciones", target = "observaciones"),
            @Mapping(source = "mascotaId", target = "mascotaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "fechaActualizacion", target = "fechaActualizacion")

    })
    Triaje toTriajeFromDto(TriajeDto dto);

    @Mappings({
            @Mapping(source = "triajeId", target = "triajeId"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "temperatura", target = "temperatura"),
            @Mapping(source = "frecuenciaCardiaca", target = "frecuenciaCardiaca"),
            @Mapping(source = "frecuenciaRespiratoria", target = "frecuenciaRespiratoria"),
            @Mapping(source = "observaciones", target = "observaciones"),
            @Mapping(source = "mascota.mascotaId", target = "mascotaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "fechaActualizacion", target = "fechaActualizacion")


    })
    List<TriajeDto> toTriajeDto(List<Triaje> triajes);

    @Mappings({
            @Mapping(source = "triajeId", target = "triajeId"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "temperatura", target = "temperatura"),
            @Mapping(source = "frecuenciaCardiaca", target = "frecuenciaCardiaca"),
            @Mapping(source = "frecuenciaRespiratoria", target = "frecuenciaRespiratoria"),
            @Mapping(source = "observaciones", target = "observaciones"),
            @Mapping(source = "mascota.mascotaId", target = "mascotaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "fechaActualizacion", target = "fechaActualizacion")


    })
    Triaje toTriaje(TriajeEntity entity);

    @InheritInverseConfiguration
    TriajeEntity toTriajeEntity(Triaje triaje);
}
