package webvet.v1.infraestructure.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webvet.v1.application.dto.*;
import webvet.v1.application.dto.response.ResponseBase;
import webvet.v1.domain.aggregates.constans.EstadoCita;
import webvet.v1.domain.ports.input.*;
import webvet.v1.infraestructure.mapper.CitaMapper;
import webvet.v1.infraestructure.mapper.ClienteMapper;
import webvet.v1.infraestructure.mapper.MascotaMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vet/")
@Tag(name = "MascotaVet", description = "API para la visualizacion de Mascotas")
public class MascotaVetController {

    private final MascotaIn mascotaIn;

    private final MascotaVetIn mascotaVetIn;

    private final ConsultaMedicaIn consultaMedicaIn;

    private final CitaIn citaIn;

    private final ClienteIn clientesIn;

    private final RazaIn razaIn;

    private final EspecieIn especieIn;

    private final TriajeIn triajeIn;

    private final CitaMapper citaMapper;

    private final MascotaMapper mascotaMapper;

    private final ClienteMapper clienteMapper;



    public MascotaVetController(MascotaIn mascotaIn, MascotaVetIn mascotaVetIn, ConsultaMedicaIn consultaMedicaIn, CitaIn citaIn, ClienteIn clienteIn, RazaIn razaIn, EspecieIn especieIn, TriajeIn triajeIn, CitaMapper citaMapper, MascotaMapper mascotaMapper, ClienteMapper clienteMapper) {
        this.mascotaIn = mascotaIn;
        this.mascotaVetIn = mascotaVetIn;
        this.consultaMedicaIn = consultaMedicaIn;
        this.citaIn = citaIn;
        this.clientesIn = clienteIn;
        this.razaIn = razaIn;
        this.especieIn = especieIn;
        this.triajeIn = triajeIn;
        this.citaMapper = citaMapper;
        this.mascotaMapper = mascotaMapper;
        this.clienteMapper = clienteMapper;
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase<List<MascotaDto>>> listarMascotas() {
        List<MascotaDto> mascotas = mascotaIn.getAllmascotas();
        if (mascotas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, "No hay mascotas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Mascotas obtenidas correctamente", mascotas));
    }

    @GetMapping("/pacientes/{veterinarioId}")
    public ResponseEntity<ResponseBase<List<PacienteVetDto>>> listarPacientesPorVeterinario(@PathVariable Long veterinarioId) {
        List<PacienteVetDto> pacientes = mascotaVetIn.listarPacientesAsignadosAlVeterinario(veterinarioId);

        if (pacientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, "No hay pacientes asignados", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Pacientes asignados encontrados", pacientes));
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResponseBase<List<ConsultaMedicaDto>>> obtenerResumen() {
        var data = consultaMedicaIn.listarConsultasResumen();
        return ResponseEntity.ok(new ResponseBase<>(200, "Consultas médicas encontradas", data));
    }

    @GetMapping("/listarCitas")
    public ResponseEntity<ResponseBase<List<CitaDto>>> getAllCitas() {
        List<CitaDto> citas = citaIn.getAllCitas();
        return ResponseEntity.ok(new ResponseBase<>(200, "Listado de citas", citas));
    }

    @GetMapping("/Listarcita/{id}")
    public ResponseEntity<ResponseBase<CitaDto>> obtenerCita(@PathVariable Long id) {
        return citaIn.foundCitaById(id)
                .map(cita -> citaMapper.toCitaDto(cita))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "Cita encontrada", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Cita no encontrado", null)));
    }

    @GetMapping("/Listarcitas/hoy")
    public ResponseEntity<ResponseBase<List<CitaDto>>> getAllCitasByToday() {
        List<CitaDto> citasListar = citaIn.getAllCitasByToday();
        return ResponseEntity.ok(new ResponseBase<>(200, "Listado de citas", citasListar));
    }

    @PutMapping("/estadoCita/{id}/{nuevaEstado}")
    public ResponseEntity<ResponseBase<CitaDto>> actualizarEstado(@PathVariable Long id, @RequestParam EstadoCita nuevoEstado) {
        Optional<CitaDto> cita = citaIn.updateEstadoCita(id, nuevoEstado);
        if (cita.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "Cita no encontrada", null));
        }

        return ResponseEntity.ok(
                new ResponseBase<>(200, "Estado de la cita actualizado exitosamente", cita.get())
        );
    }

    @GetMapping("/buscar/mascota/ByclienteId/{clienteId}")
    public ResponseEntity<ResponseBase<List<MascotaDto>>> obtenerMascotasPorCliente(@PathVariable Long clienteId) {
        List<MascotaDto> mascotas = mascotaIn.findMascotaByCliente(clienteId);

        if (mascotas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "El cliente no tiene mascotas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Mascotas del cliente obtenidas correctamente", mascotas));
    }

    @GetMapping("/buscar/mascotabyId/{id}")
    public ResponseEntity<ResponseBase<MascotaDto>> obternerMascota(@PathVariable Long id) {
        return mascotaIn.findMascotabyid(id)
                .map(mascota -> mascotaMapper.toMascotaDto(mascota))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "mascota encontrada", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "mascota no encontrada", null)));
    }

    @GetMapping("/buscar/clienteById/{id}")
    public ResponseEntity<ResponseBase<ClienteDto>> obtenerCliente(@PathVariable Long id) {
        return clientesIn.obtenerClientePorId(id)
                .map(cliente -> clienteMapper.toClienteDto(cliente))
                .map(dto -> ResponseEntity.ok(new ResponseBase<>(200, "Cliente encontrado", dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "Cliente no encontrado", null)));
    }

    @GetMapping("/listarEspeciesVet")
    public ResponseEntity<ResponseBase<List<EspecieDto>>>getAllespecies(){
        List<EspecieDto> especies = especieIn.getAllEspecies();

        if (especies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen especies registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Las especies se obtuvieron", especies));


    }

    @GetMapping("/listarRazasVet")
    public ResponseEntity<ResponseBase<List<RazaDto>>>geAllRazas(){
        List<RazaDto> razas = razaIn.getAllRazas();

        if (razas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseBase<>(204, " No existen razas registradas", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Las razas se obtuvieron", razas));


    }

    @GetMapping("/Listartriaje/mascota/{mascotaId}")
    public ResponseEntity<ResponseBase<List<TriajeDto>>> findMascotaById(@PathVariable Long mascotaId) {
        List<TriajeDto> triajes = triajeIn.findTriajeByMascota(mascotaId);

        if (triajes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseBase<>(404, "La mascita no tiene triaje registrado", Collections.emptyList()));
        }

        return ResponseEntity.ok(new ResponseBase<>(200, "Triaje de la mascota obtenidas correctamente", triajes));
    }

    @PutMapping("/triaje/actualizar-triaje/{mascotaId}")
    public ResponseEntity<ResponseBase<TriajeDto>> updateUltimoTriajePorMascota(@PathVariable Long mascotaId) {
        return triajeIn.updateTriajeByMascotaId(mascotaId)
                .map(triajeDto -> new ResponseEntity<>(
                        new ResponseBase<>(200, "Último triaje de la mascota actualizado correctamente", triajeDto),
                        HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseBase<>(404, "No se encontró un triaje previo para esta mascota", null)));
    }

}
