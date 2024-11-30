package dedalus.backend.service.read;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.model.InputData;

public interface ReadInputService {
    InputData readAndMapInputDtoToInputData (InputDTO inputDTO);
    void saveInputData(InputData inputData);
    InputData getPreviousInput();

}