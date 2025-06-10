package webvet.v1.domain.ports.input;

import webvet.v1.application.dto.ClienteDto;
import webvet.v1.domain.aggregates.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteIn {

    Optional<Cliente> CrearCliente(ClienteDto clienteDto);

    List<ClienteDto>  listarClientes();

    Optional<ClienteDto> buscarClientePorId(Integer id);




}
