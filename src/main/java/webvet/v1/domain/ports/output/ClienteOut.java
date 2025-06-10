package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteOut {
    Optional<Cliente> CrearCliente (Cliente cliente);

    List<Cliente> ListarClientes ();

    Optional<Cliente> EncontrarCliente (Cliente cliente);



}
