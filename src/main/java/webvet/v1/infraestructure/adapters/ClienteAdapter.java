package webvet.v1.infraestructure.adapters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webvet.v1.domain.aggregates.model.Cliente;
import webvet.v1.domain.ports.output.ClienteOut;
import webvet.v1.infraestructure.entity.ClienteEnitity;
import webvet.v1.infraestructure.mapper.ClienteMapper;
import webvet.v1.infraestructure.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteAdapter implements ClienteOut {

   @Autowired
    ClienteMapper clienteMapper;

   @Autowired
   ClienteRepository clienteRepository;

   @Override
   public Optional<Cliente> CrearCliente(Cliente cliente){

       ClienteEnitity clienteEntity = clienteMapper.toClienteEnitity(cliente);
       ClienteEnitity clienteEnitityCreate = clienteRepository.save(clienteEntity);

       return Optional.of(clienteMapper.toCliente(clienteEnitityCreate));


   }

   @Override
    public List<Cliente> ListarClientes(){
       List<ClienteEnitity> clienteEntity = clienteRepository.findAll();

       return clienteEntity.stream()
               .map(clienteMapper::toCliente)
               .collect(Collectors.toList());

   }

   @Override
    public Optional<ClienteEnitity> EncontrarCliente(String nombre){

       return clienteRepository.findByNombre(nombre);

   }

   @Override
    public Optional<Cliente>EncontrarId(Long clienteId){
       return clienteRepository.findById(clienteId)
               .map(clienteMapper::toCliente);
   }






}
