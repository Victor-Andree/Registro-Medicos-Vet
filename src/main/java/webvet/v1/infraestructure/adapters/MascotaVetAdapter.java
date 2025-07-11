package webvet.v1.infraestructure.adapters;


import org.springframework.stereotype.Service;
import webvet.v1.application.dto.PacienteVetDto;
import webvet.v1.domain.ports.output.MascotaVetOut;
import webvet.v1.infraestructure.entity.CitaEntity;
import webvet.v1.infraestructure.entity.ClienteEntity;
import webvet.v1.infraestructure.repository.CitaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaVetAdapter implements MascotaVetOut {

    private final CitaRepository citaRepository;

    public MascotaVetAdapter(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public List<PacienteVetDto> obtenerPacientesPorVeterinarioId(Long veterinarioId) {
        List<CitaEntity> citas = citaRepository.findByVeterinario_veterinarioId(veterinarioId);

        return citas.stream().map(cita -> {
            PacienteVetDto dto = new PacienteVetDto();
            dto.setNombreMascota(cita.getMascota().getNombre());
            dto.setEspecie(cita.getMascota().getRaza().getEspecie().getNombre());
            dto.setRaza(cita.getMascota().getRaza().getNombre());
            dto.setEdad(cita.getMascota().getEdad());

            ClienteEntity cliente = cita.getCliente();
            dto.setNombrePropietario(cliente.getNombre() + " " + cliente.getApellido());
            dto.setTelefonoPropietario(cliente.getTelefono());

            dto.setUltimaVisita(cita.getFechaRegistro().toLocalDate()); // último triaje
            dto.setProximaCita(cita.getFechaRegistro().toLocalDate()); // próxima cita

            return dto;
        }).distinct().collect(Collectors.toList());
    }
}
