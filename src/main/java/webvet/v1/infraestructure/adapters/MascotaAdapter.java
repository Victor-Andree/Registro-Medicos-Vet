package webvet.v1.infraestructure.adapters;

import org.springframework.stereotype.Service;
import webvet.v1.application.dto.MascotaDto;
import webvet.v1.domain.aggregates.model.Mascota;
import webvet.v1.domain.ports.output.MascotaOut;
import webvet.v1.infraestructure.entity.MascotaEntity;
import webvet.v1.infraestructure.mapper.MascotaMapper;
import webvet.v1.infraestructure.repository.ClienteRepository;
import webvet.v1.infraestructure.repository.MascotaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MascotaAdapter implements MascotaOut {

    private final MascotaMapper mascotaMapper;

    private final MascotaRepository mascotaRepository;
    private final ClienteRepository clienteRepository;

    public MascotaAdapter(MascotaMapper mascotaMapper, MascotaRepository mascotaRepository, ClienteRepository clienteRepository) {
        this.mascotaMapper = mascotaMapper;
        this.mascotaRepository = mascotaRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Optional<Mascota> createMascota(Mascota mascota){

        MascotaEntity mascotaEntity = mascotaMapper.toMascotaEntity(mascota);
        MascotaEntity mascotaCreateEntity = mascotaRepository.save(mascotaEntity);

        return Optional.of(mascotaMapper.toMascota(mascotaCreateEntity));

    }

    @Override
    public List<Mascota> listarMascotas(){
        List<MascotaEntity> mascotaEntity = mascotaRepository.findAll();

        return mascotaEntity.stream()
                .map(mascotaMapper::toMascota)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<MascotaEntity> findBynme (String nombre){

        return mascotaRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Mascota> findByIdMascota (Long id){
        return mascotaRepository.findById(id)
                .map(mascotaMapper::toMascota);
    }

    @Override
    public Optional<Mascota> updateMascota(Mascota mascota){
        return mascotaRepository.findById(mascota.getMascotaId())
                .map(entityExistente -> {
                    entityExistente.setNombre(mascota.getNombre());
                    entityExistente.setRaza(mascota.getRaza());
                    entityExistente.setEspecie(mascota.getEspecie());

                    if (mascota.getCliente() != null){
                        clienteRepository.findById(mascota.getCliente().getClienteId())
                                .ifPresent(entityExistente::setCliente);
                    }

                    MascotaEntity updateEntity = mascotaRepository.save(entityExistente);
                    return mascotaMapper.toMascota(updateEntity);



                });

    }

    @Override
    public List<Mascota> findMascotasByClienteId(Long clienteId){
        List<MascotaEntity> entidades = mascotaRepository.findByCliente_ClienteId(clienteId);
        return entidades.stream()
                .map(mascotaMapper::toMascota)
                .collect(Collectors.toList());
    }



}
