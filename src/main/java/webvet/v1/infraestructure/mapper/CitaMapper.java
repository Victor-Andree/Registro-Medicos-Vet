package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.CitaDto;
import webvet.v1.domain.aggregates.model.Cita;
import webvet.v1.infraestructure.entity.CitaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mappings({

            @Mapping(source = "citaId", target = "citaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "tipoServicio" , target = "tipoServicio"),
            @Mapping(source = "mascota" , target = "mascota"),
            @Mapping(source = "cliente" , target = "cliente"),
            @Mapping(source = "veterinario", target = "veterinario"),
            @Mapping(source = "motivo", target = "motivo"),
            @Mapping(source = "estadoCita" , target = "estadoCita"),

    })
    Cita toCita(CitaEntity citaEntity);

    @Mappings({

            @Mapping(source = "citaId", target = "citaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "tipoServicio.tipoServicioId" , target = "tipoServicioId"),
            @Mapping(source = "mascota.mascotaId" , target = "mascotaId"),
            @Mapping(source = "cliente.clienteId" , target = "clienteId"),
            @Mapping(source = "veterinario.veterinarioId", target = "veterinarioId"),
            @Mapping(source = "motivo", target = "motivo"),
            @Mapping(source = "estadoCita" , target = "estadoCita"),

    })
    CitaDto toCitaDto(Cita cita);

    @Mappings({

            @Mapping(source = "citaId", target = "citaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "tipoServicioId" , target = "tipoServicio.tipoServicioId"),
            @Mapping(source = "mascotaId" , target = "mascota.mascotaId"),
            @Mapping(source = "clienteId" , target = "cliente.clienteId"),
            @Mapping(source = "veterinarioId", target = "veterinario.veterinarioId"),
            @Mapping(source = "motivo", target = "motivo"),
            @Mapping(source = "estadoCita" , target = "estadoCita"),

    })
    Cita toCitaFromDto(CitaDto citaDto);

    @Mappings({

            @Mapping(source = "citaId", target = "citaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "tipoServicio.tipoServicioId" , target = "tipoServicioId"),
            @Mapping(source = "mascota.mascotaId" , target = "mascotaId"),
            @Mapping(source = "cliente.clienteId" , target = "clienteId"),
            @Mapping(source = "veterinario.veterinarioId", target = "veterinarioId"),
            @Mapping(source = "motivo", target = "motivo"),
            @Mapping(source = "estadoCita" , target = "estadoCita"),

    })
    List<CitaDto> toCitaDtoList(List<Cita> citas);
    @Mappings({

            @Mapping(source = "citaId", target = "citaId"),
            @Mapping(source = "fechaRegistro", target = "fechaRegistro"),
            @Mapping(source = "tipoServicio.tiposervicioId" , target = "tipoServicioId"),
            @Mapping(source = "mascota.mascotaId" , target = "mascotaId"),
            @Mapping(source = "cliente.clienteId" , target = "clienteId"),
            @Mapping(source = "veterinario.veterinarioId", target = "veterinarioId"),
            @Mapping(source = "motivo", target = "motivo"),
            @Mapping(source = "estadoCita" , target = "estadoCita"),

    })
    CitaDto toCitaDtoFromEntity(CitaEntity citaEntity);

    @InheritInverseConfiguration
    CitaEntity toCitaEntity(Cita cita);




}
