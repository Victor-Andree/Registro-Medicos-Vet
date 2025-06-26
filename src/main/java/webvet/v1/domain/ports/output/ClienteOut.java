package webvet.v1.domain.ports.output;

import webvet.v1.domain.aggregates.model.Cliente;
import webvet.v1.infraestructure.entity.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteOut {
    Optional<Cliente> CrearCliente (Cliente cliente);

    List<Cliente> ListarClientes ();

    Optional<ClienteEntity> EncontrarCliente (String nombre);

    Optional<Cliente> EncontrarId (Long id);

    Optional<Cliente> updateCliente (Cliente cliente);



}
