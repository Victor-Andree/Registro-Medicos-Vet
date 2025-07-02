package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.ClienteDto;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.ports.input.ClienteIn;
import webvet.v1.infraestructure.mapper.ClienteMapper;

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
    public ResponseEntity<ResponseBase<ClienteDto>> crearCliente(@RequestBody @Valid ClienteDto clienteDto) {
        try {
            Optional<ClienteDto> clienteCreado = clientesIn.crearCliente(clienteDto);

            if (clienteCreado.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseBase<>(201, "Cliente creado correctamente", clienteCreado.get()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseBase<>(400, "No se pudo crear el cliente", null));
            }

        } catch (Exception e) {
            // Loguear el error si tienes logger
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseBase<>(500, "Ocurrió un error al crear el cliente", null));
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

    @GetMapping("/cliente/nombre/{nombre}")
    public ResponseEntity<ResponseBase<List<ClienteDto>>> obtenerClientePorNombre(@PathVariable String nombre) {
        List<ClienteDto> clientes = clientesIn.obtenerClientePorNombre(nombre);

        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "No se encontraron clientes con el nombre: " + nombre, null));
        } else {
            return ResponseEntity.ok(new ResponseBase<>(200, "Clientes encontrados", clientes));
        }
    }


    @PutMapping("/cliente/editar/{id}")
    public ResponseEntity<ResponseBase<ClienteDto>> actualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteDto) {

        if (clienteDto.getClienteId() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseBase<>(400, "Debe proporcionar el ID del cliente para actualizar", null));
        }

        return clientesIn.updateCliente(clienteDto)
                .map(clienteActualizado -> new ResponseEntity<>(
                        new ResponseBase<>(200, "Cliente actualizado correctamente", clienteActualizado),
                        HttpStatus.OK
                ))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "No se encontró al cliente con el ID proporcionado", null)));


    }



}
