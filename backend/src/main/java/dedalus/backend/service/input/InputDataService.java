package dedalus.backend.service.input;

import dedalus.backend.dto.InputDTO;

public interface InputDataService {

    void saveInput(InputDTO inputDTO);

    InputDTO getLastInput();

}
