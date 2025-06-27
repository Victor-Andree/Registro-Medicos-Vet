package webvet.v1.infraestructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.MascotaDto;
import webvet.v1.domain.aggregates.model.Mascota;
import webvet.v1.infraestructure.entity.MascotaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    @Mappings({
            @Mapping(source = "mascotaId", target = "mascotaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "edad" , target = "edad"),
            @Mapping(source = "raza", target = "raza"),
            @Mapping(source = "cliente", target = "cliente"),
    })
    Mascota toMascota(MascotaEntity mascotaEntity);

    @InheritInverseConfiguration
    MascotaEntity toMascotaEntity(Mascota mascota);


    @Mappings({
            @Mapping(source = "mascotaId", target = "mascotaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "edad" , target = "edad"),
            @Mapping(source = "raza.razaId", target = "razaId"),
            @Mapping(source = "cliente.clienteId", target = "clienteId")
    })

    MascotaDto toMascotaDto(Mascota mascota);



    @Mappings({
            @Mapping(source = "mascotaId", target = "mascotaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "edad" , target = "edad"),
            @Mapping(source = "razaId", target = "raza.razaId"),
            @Mapping(source = "clienteId", target = "cliente.clienteId")
    })

    Mascota toMascotaFromDto(MascotaDto mascotaDto);


    @Mappings({
            @Mapping(source = "mascotaId", target = "mascotaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "edad" , target = "edad"),
            @Mapping(source = "raza.razaId", target = "razaId"),
            @Mapping(source = "cliente.clienteId", target = "clienteId")
    })

    List<MascotaDto> toMascotaDto(List<Mascota> mascotas);


    @Mappings({
            @Mapping(source = "mascotaId", target = "mascotaId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "edad" , target = "edad"),
            @Mapping(source = "raza.razaId", target = "razaId"),
            @Mapping(source = "cliente.clienteId", target = "clienteId")
    })

    MascotaDto toMascotaDtoFromEntity(MascotaEntity mascotaEntity);

}

