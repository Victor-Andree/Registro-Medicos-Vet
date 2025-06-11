package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webvet.v1.application.dto.ClienteDto;
import webvet.v1.domain.ports.input.ClienteIn;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authentication/")
@Tag(name = "Cliente", description = "API para creacion de Clientes")

public class ClienteController {

    private final ClienteIn clientesIn;


    public ClienteController(ClienteIn clientesIn) {
        this.clientesIn = clientesIn;
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

}
