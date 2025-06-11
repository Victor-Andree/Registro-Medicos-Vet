package webvet.v1.application.useCase;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.MascotaDto;
import webvet.v1.domain.aggregates.model.Mascota;
import webvet.v1.domain.ports.input.MascotaIn;
import webvet.v1.domain.ports.output.MascotaOut;
import webvet.v1.infraestructure.mapper.MascotaMapper;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaUseCase implements MascotaIn {

    private final MascotaOut mascotaOut;

    private final MascotaMapper mascotaMapper;


    public MascotaUseCase(MascotaOut mascotaOut, MascotaMapper mascotaMapper) {
        this.mascotaOut = mascotaOut;
        this.mascotaMapper = mascotaMapper;
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





}
