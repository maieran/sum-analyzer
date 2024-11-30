package dedalus.backend.service.read;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.model.InputData;
import dedalus.backend.service.input.InputDataService;

@Service
public class ReadInputServiceImpl implements ReadInputService {

    
    @Autowired
    private InputDataService inputDataService;


    @Override
    public InputData getPreviousInput() {
        return inputDataService.getLastInput();
    }

    @Override
    public InputData readAndMapInputDtoToInputData(InputDTO inputDTO) {
        InputData inputData = new InputData();
        inputData.setSumValue(inputDTO.getValue());
        return inputData;
    }

    @Override
    public void saveInputData(InputData inputData) {
        inputData.setTimestamp(LocalDateTime.now());
        inputDataService.saveInput(inputData);
    }
  
    
    
}
