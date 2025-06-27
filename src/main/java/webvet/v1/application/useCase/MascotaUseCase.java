package webvet.v1.application.useCase;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.MascotaDto;
import webvet.v1.domain.aggregates.constans.EstadoMascota;
import webvet.v1.domain.aggregates.constans.EstadoUsuario;
import webvet.v1.domain.aggregates.model.Mascota;
import webvet.v1.domain.aggregates.model.Usuario;
import webvet.v1.domain.ports.input.MascotaIn;
import webvet.v1.domain.ports.output.MascotaOut;
import webvet.v1.infraestructure.mapper.MascotaMapper;
import webvet.v1.infraestructure.repository.MascotaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaUseCase implements MascotaIn {

    private final MascotaOut mascotaOut;
    private final MascotaMapper mascotaMapper;
    private final MascotaRepository mascotaRepository;


    public MascotaUseCase(MascotaOut mascotaOut, MascotaMapper mascotaMapper, MascotaRepository mascotaRepository) {
        this.mascotaOut = mascotaOut;
        this.mascotaMapper = mascotaMapper;
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public Optional<MascotaDto> createMascota(MascotaDto mascotaDto){
        Mascota mascota = mascotaMapper.toMascotaFromDto(mascotaDto);

        Optional<Mascota> mascotaCreada = mascotaOut.createMascota(mascota);

        return mascotaCreada.map(mascotaMapper::toMascotaDto);
    }

    @Override
    public List<MascotaDto> getAllmascotas(){
        List<Mascota> mascotas = mascotaOut.listarMascotas();

        if (mascotas.isEmpty()){
            throw new EntityNotFoundException("No se encontraron mascotas registradas");
        }

        return mascotaMapper.toMascotaDto(mascotas);

    }

    @Override
    public Optional<Mascota> findMascotabyid(Long id){
        return mascotaOut.findByIdMascota(id);

    }

    @Override
    public Optional<MascotaDto> findMascotabyname (String nombre){
        return mascotaOut.findBynme(nombre)
                .map(mascotaMapper::toMascotaDtoFromEntity);
    }

    @Override
    public Optional<MascotaDto> updateMascota (MascotaDto mascotaDto){

        Mascota mascota = mascotaMapper.toMascotaFromDto(mascotaDto);

        Optional<Mascota> mascotaActualizada = mascotaOut.updateMascota(mascota);

        return mascotaActualizada.map(mascotaMapper::toMascotaDto);

    }

    @Override
    public List<MascotaDto> findMascotaByCliente(Long clienteId) {
        List<Mascota> mascotas = mascotaOut.findMascotasByClienteId(clienteId);
        return mascotas.stream()
                .map(mascotaMapper::toMascotaDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MascotaDto> ChangestatusMascota(Long mascotaId){
        Optional<Mascota> mascotas = mascotaOut.findByIdMascota(mascotaId);
        if (mascotas.isEmpty()) return Optional.empty();

        Mascota mascota = mascotas.get();
        mascota.setEstado(EstadoMascota.FALLECIDO);

        return mascotaOut.updateMascota(mascota)
                .map(mascotaMapper::toMascotaDto);
    }





}
