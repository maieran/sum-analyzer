package dedalus.backend.service.analyse;

import java.util.Collections;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import dedalus.backend.dto.ResultDTO;
import dedalus.backend.model.InputData;

@Service
public class AnalyseServiceImpl implements AnalyseService {


    // @Override
    // public ResultDTO analyseSum(InputData previousData, InputData currentData) {
    //    ResultDTO resultDTO = new ResultDTO();
        
       


    //     if (previousData != null) {
    //         double currentSum = currentData.getSumValue();
    //         double previousSum = previousData.getSumValue();
    //         double difference = currentSum - previousSum;
    //         resultDTO.setOutputValue(difference);

    //         return resultDTO;
    //     } 
        
    //     //else
    //     resultDTO.setOutputValue(Integer.MIN_VALUE);

    //     return resultDTO;
    // }


    @Override
    public ResultDTO analyseSum(InputData previousData, InputData currentData) {
       ResultDTO resultDTO = new ResultDTO();
       double[] coinsAndBanknotes = {200, 100, 50, 20, 10, 5, 2, 1, 0.50, 0.20, 0.10, 0.05, 0.01}; //unser 
        
        if (previousData != null) {
            double currentSum = currentData.getSumValue();
            double previousSum = previousData.getSumValue();
            
            //generateSumFitting


            //cleanSumFitting


            //compareSumFitting



            return resultDTO;
        } 
        
        //else
        //resultDTO.setOutputValue();

        return resultDTO;
    }

    private TreeMap<Double, Integer> generateSumFitting (double inputSum, double[] coinsAndBankNotes) {
        TreeMap<Double, Integer> sumFitting = new TreeMap<>(Collections.reverseOrder());

        for (double currentFit : coinsAndBankNotes) {
            if (inputSum >= currentFit) {
                int amount = (int) (inputSum / currentFit);
                sumFitting.put(currentFit, amount);
                inputSum %= currentFit;
            }
        }
        return sumFitting;
    }

    private TreeMap<Double, Integer> compareOldSumWithNewSum(TreeMap<Double, Integer> previousSumFitting, TreeMap<Double, Integer> currentSumFitting) {
        TreeMap<Double, Integer> differenceFittings = new TreeMap<>(Collections.reverseOrder());


        return differenceFittings; 
    }

}