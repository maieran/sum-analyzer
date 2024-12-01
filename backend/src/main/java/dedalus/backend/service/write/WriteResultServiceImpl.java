package dedalus.backend.service.write;

import java.util.Map;

import org.springframework.stereotype.Service;

import dedalus.backend.dto.ResultDTO;

@Service
public class WriteResultServiceImpl implements WriteResultService {


    @Override
    public void write(ResultDTO resultDTO) {
        String resultMessage = "Schein-oder Münzenart: %.2f€, Differenz: %+d\n";
        StringBuilder result = new StringBuilder();
        
        for (Map.Entry<Double, Integer> entry : resultDTO.getResultTree().entrySet()) {
            double coinOrBankNotesValue = entry.getKey();
            int difference = entry.getValue();
            result.append(String.format(resultMessage, coinOrBankNotesValue, difference));
        }

        System.out.println(result.toString());
    }
}