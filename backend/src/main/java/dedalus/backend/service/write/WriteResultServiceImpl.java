package dedalus.backend.service.write;

import org.springframework.stereotype.Service;

import dedalus.backend.dto.ResultDTO;

@Service
public class WriteResultServiceImpl implements WriteResultService {


    @Override
    public void write(ResultDTO resultDTO) {
        String resultMessage;

        resultMessage = String.format("Current sum: ",resultDTO.getOutputValue());
        System.out.println(resultMessage);
    }
}