package dedalus.backend.service.input;

import dedalus.backend.model.InputData;

public interface InputDataService {

    void saveInput(InputData InputData);

    InputData getLastInput();

}
