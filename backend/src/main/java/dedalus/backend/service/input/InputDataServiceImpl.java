package dedalus.backend.service.input;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.model.InputData;
import dedalus.backend.repository.InputDataRepository;

@Service
public class InputDataServiceImpl implements InputDataService {

    @Autowired
    private InputDataRepository inputDataRepository;


    // Input wird hier zwischengespeichert
    public void saveInput(InputDTO inputDTO) {
        InputData inputData = new InputData();
        inputData.setSumValue(inputDTO.getValue());
        inputData.setTimestamp(LocalDateTime.now());
        inputDataRepository.save(inputData);
    }


    @Override
    public InputDTO getLastInput() {
        Optional<InputData> latestInput = inputDataRepository.findLatestInput();
        if (latestInput.isPresent()) {
            InputData lastData = latestInput.get();
            InputDTO lastInputDTO = new InputDTO();
            lastInputDTO.setValue(lastData.getSumValue());
            return lastInputDTO;
        }

        return null;
    }
    
}
