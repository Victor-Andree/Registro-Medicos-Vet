package webvet.v1.infraestructure.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import webvet.v1.application.dto.ClienteDto;
import webvet.v1.domain.aggregates.model.Cliente;
import webvet.v1.infraestructure.entity.ClienteEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mappings({
            @Mapping(source = "clienteId", target = "clienteId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "ciudad", target = "ciudad"),
    })

    Cliente toCliente(ClienteEntity clienteEntity);

    @InheritInverseConfiguration
    ClienteEntity toClienteEntity(Cliente cliente);

    @Mappings({
            @Mapping(source = "clienteId", target = "clienteId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "ciudad", target = "ciudad"),
    })
    ClienteDto toClienteDto(Cliente cliente);

    @Mappings({
            @Mapping(source = "clienteId", target = "clienteId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "ciudad", target = "ciudad"),
    })
    Cliente toClienteFromDto(ClienteDto clienteDto);

    @Mappings({
            @Mapping(source = "clienteId", target = "clienteId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "ciudad", target = "ciudad"),
    })
    List<ClienteDto> toClienteDto(List<Cliente> clientes);

    @Mappings({
            @Mapping(source = "clienteId", target = "clienteId"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "ciudad", target = "ciudad"),
    })
    ClienteDto toClienteDtoFromEntity(ClienteEntity clienteEntity);


}





