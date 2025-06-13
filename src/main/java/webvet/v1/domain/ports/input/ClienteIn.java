package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.ClienteDto;
import webvet.v1.domain.aggregates.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteIn {

    Optional<ClienteDto> crearCliente(ClienteDto clienteDto);

    List<ClienteDto> obtenerClientes();

    Optional<Cliente> obtenerClientePorId(Long id);

    Optional<ClienteDto> obtenerClientePorNombre(String nombre);

    Optional<ClienteDto> updateCliente(ClienteDto clienteDto);





}
