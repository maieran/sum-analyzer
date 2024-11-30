package dedalus.backend.service.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.service.input.InputDataService;

@Service
public class ReadInputServiceImpl implements ReadInputService {

    
    @Autowired
    private InputDataService inputDataService;

    @Override
    public void read(InputDTO inputDTO) {
        inputDataService.saveInput(inputDTO);
    }

    @Override
    public InputDTO getPreviousInput() {
        return inputDataService.getLastInput();
    }
  
    
    
}
