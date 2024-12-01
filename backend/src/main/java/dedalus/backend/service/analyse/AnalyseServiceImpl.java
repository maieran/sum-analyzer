package dedalus.backend.service.analyse;

import java.time.LocalDateTime;
import java.util.Collections;
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
            getDifferenceTreeMap(previousData, currentData, resultDTO);
            
            return resultDTO;
        } 
        
        //else case
        getCurrentTreeMap(currentData, resultDTO);

        return resultDTO;
    }

    private void getCurrentTreeMap(InputData currentData, ResultDTO resultDTO) {
        double currentSum = currentData.getSumValue();
        TreeMap<Double, Integer> currentTreeMap = doSumFitting(currentSum);
        resultDTO.setResultTree(currentTreeMap);
    }

    private void getDifferenceTreeMap(InputData previousData, InputData currentData, ResultDTO resultDTO) {
        double currentSum = currentData.getSumValue();
        double previousSum = previousData.getSumValue();

        TreeMap<Double, Integer> currentTreeMap = doSumFitting(currentSum);
        TreeMap<Double, Integer> previousTreeMap = doSumFitting(previousSum);

        TreeMap<Double, Integer> differenceTreeMap = doDifferenceSumFitting(currentTreeMap, previousTreeMap);

        resultDTO.setResultTree(differenceTreeMap);
    }

    private TreeMap<Double, Integer> doSumFitting (double inputSum) {
        TreeMap<Double, Integer> sumFitting = new TreeMap<>(Collections.reverseOrder());
        double[] coinsAndBanknotes = {200, 100, 50, 20, 10, 5, 2, 1, 0.50, 0.20, 0.10, 0.05, 0.01};

        for (double currentFit : coinsAndBanknotes) {
            if (inputSum >= currentFit) {
                int amount = (int) (inputSum / currentFit);
                sumFitting.put(currentFit, amount);
                inputSum %= currentFit;
            }
        }
        return sumFitting;
    }

    private TreeMap<Double, Integer> doDifferenceSumFitting (TreeMap<Double, Integer> previousSumFitting, TreeMap<Double, Integer> currentSumFitting) {
        TreeMap<Double, Integer> differenceFittings = new TreeMap<>(Collections.reverseOrder());
        double[] coinsAndBanknotes = {200, 100, 50, 20, 10, 5, 2, 1, 0.50, 0.20, 0.10, 0.05, 0.01};

        //Initialisiere 
        for (double coinOrBankNote : coinsAndBanknotes) {
            differenceFittings.put(coinOrBankNote, 0);
        }

        for (Double coinOrBankNotesValue : differenceFittings.keySet()) {
            int alteAnzahl = previousSumFitting.getOrDefault(coinOrBankNotesValue, 0);
            int neueAnzahl = currentSumFitting.getOrDefault(coinOrBankNotesValue, 0);
            differenceFittings.put(coinOrBankNotesValue, alteAnzahl - neueAnzahl);
        }

        return differenceFittings; 
    }

}