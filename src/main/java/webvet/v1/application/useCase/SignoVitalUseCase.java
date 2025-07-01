package webvet.v1.application.useCase;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import webvet.v1.application.dto.SignoVitalDto;
import webvet.v1.domain.aggregates.model.SignoVital;
import webvet.v1.domain.ports.input.SignoVitalIn;
import webvet.v1.domain.ports.output.SignoVitalOut;
import webvet.v1.infraestructure.mapper.SignoVitalMapper;
import webvet.v1.infraestructure.repository.SignoVitalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SignoVitalUseCase implements SignoVitalIn {

    private final SignoVitalRepository signoVitalRepository;
    private  final SignoVitalOut signoVitalOut;
    private final SignoVitalMapper signoVitalMapper;

    public SignoVitalUseCase(SignoVitalRepository signoVitalRepository, SignoVitalOut signoVitalOut, SignoVitalMapper signoVitalMapper) {
        this.signoVitalRepository = signoVitalRepository;
        this.signoVitalOut = signoVitalOut;
        this.signoVitalMapper = signoVitalMapper;
    }

    @Override
    public Optional<SignoVitalDto> createSignoVital(SignoVitalDto signoVitalDto){

        SignoVital signoVital = signoVitalMapper.toSignoVitalFromDto(signoVitalDto);
        Optional<SignoVital> signoVitalCreado =signoVitalOut.createSignoVital(signoVital);

        return signoVitalCreado.map(signoVitalMapper::toSignoVitalDto);
    }

    @Override
    public List<SignoVitalDto> getAllSignoVitales(){
        List<SignoVital> signoVitals = signoVitalOut.getAllSignoVitales();

        if (signoVitals.isEmpty()){
            throw new EntityNotFoundException("No signosVitales encontrados");
        }

        return signoVitalMapper.toSignoVitalDto(signoVitals);
    }
}
