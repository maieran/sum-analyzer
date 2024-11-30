package dedalus.backend.service.analyse;

import java.util.TreeMap;

import org.springframework.stereotype.Service;

import dedalus.backend.dto.ResultDTO;
import dedalus.backend.model.InputData;

@Service
public class AnalyseServiceImpl implements AnalyseService {


    @Override
    public ResultDTO analyseSum(InputData previousData, InputData currentData) {
       ResultDTO resultDTO = new ResultDTO();
        
       


        if (previousData != null) {
            double currentSum = currentData.getSumValue();
            double previousSum = previousData.getSumValue();
            double difference = currentSum - previousSum;
            resultDTO.setOutputValue(difference);

            return resultDTO;
        } 
        
        //else
        resultDTO.setOutputValue(Integer.MIN_VALUE);

        return resultDTO;
    }

}