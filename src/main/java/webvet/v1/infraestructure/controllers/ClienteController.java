package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.ClienteDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.aggregates.model.Cliente;
import webvet.v1.domain.ports.input.ClienteIn;
import webvet.v1.infraestructure.entity.ClienteEnitity;
import webvet.v1.infraestructure.mapper.ClienteMapper;
import webvet.v1.infraestructure.repository.ClienteRepository;

import java.util.*;

@RestController
@RequestMapping("/api/v1/asistente/")
@Tag(name = "Cliente", description = "API para creacion de Clientes")

public class ClienteController {

    private final ClienteIn clientesIn;
    private final ClienteMapper clienteMapper;


    public ClienteController(ClienteIn clientesIn, ClienteMapper clienteMapper) {
        this.clientesIn = clientesIn;
        this.clienteMapper = clienteMapper;
    }


    @PostMapping("/crearCliente")
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDto clienteDto) {
        Optional<ClienteDto> clienteCreado = clientesIn.crearCliente(clienteDto);

        if (clienteCreado.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Cliente creado correctamente", "cliente", clienteCreado.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensaje", "No se pudo crear el cliente"));
        }
    }

    @GetMapping("/listarClientes")
    public ResponseEntity<ResponseBase<List<ClienteDto>>>obtenerClientes(){
        List<ClienteDto> clientes = clientesIn.obtenerClientes();

        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen Clientes registrados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Los clientes se obtuvieron", clientes));


    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ResponseBase<ClienteDto>> obtenerCliente(@PathVariable Long id) {
        return clientesIn.obtenerClientePorId(id)
                .map(cliente -> clienteMapper.toClienteDto(cliente))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "Cliente encontrado", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Cliente no encontrado", null)));
    }



}
