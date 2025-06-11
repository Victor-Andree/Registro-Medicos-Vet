package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.ClienteDto;
import webvet.v1.domain.aggregates.model.Cliente;
import webvet.v1.domain.ports.input.ClienteIn;
import webvet.v1.domain.ports.output.ClienteOut;
import webvet.v1.infraestructure.mapper.ClienteMapper;

import java.util.List;
import java.util.Optional;


@Service
public class ClienteUseCase implements ClienteIn {

    @Autowired
    private ClienteOut clienteOut;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Optional<ClienteDto> crearCliente(ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toClienteFromDto(clienteDto);

        Optional<Cliente> clienteCreado = clienteOut.CrearCliente(cliente);

        return clienteCreado.map(clienteMapper::toClienteDto);
    }



    @Override
    public Cliente  obtenerClientePorId(Long id) {
        return clienteOut.EncontrarId(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el Id" + id));

    }

    @Override
    public List<ClienteDto> obtenerClientes(){
        List<Cliente>clientes = clienteOut.ListarClientes();

        if(clientes.isEmpty()){
            throw new EntityNotFoundException("No se encontro el Cliente");
        }

        return clienteMapper.toClienteDto(clientes);

    }

}


