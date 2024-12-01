package dedalus.backend.service.input;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dedalus.backend.model.InputData;
import dedalus.backend.repository.InputDataRepository;

@Service
public class InputDataServiceImpl implements InputDataService {

    @Autowired
    private InputDataRepository inputDataRepository;


    @Override
    public void saveInput(InputData inputData) {
        inputDataRepository.save(inputData);
    }


    @Override
    public InputData getLastInput() {
        Optional<InputData> latestInput = inputDataRepository.findLatestInput();
        if (latestInput.isPresent()) {
            return latestInput.get();
        }

        return null;
    }


    
}
