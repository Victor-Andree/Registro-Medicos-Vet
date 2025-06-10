package webvet.v1.infraestructure.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import webvet.v1.domain.ports.output.ClienteOut;
import webvet.v1.infraestructure.repository.ClienteRepository;

public class ClienteAdapter implements ClienteOut {

    @Autowired
    private final ClienteRepository clienteRepository;



}
