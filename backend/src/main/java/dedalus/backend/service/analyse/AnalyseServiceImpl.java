package dedalus.backend.service.analyse;

import java.util.Collections;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import dedalus.backend.dto.ResultDTO;
import dedalus.backend.model.InputData;

@Service
public class AnalyseServiceImpl implements AnalyseService {
    public static double[] coinsAndBanknotes = {200, 100, 50, 20, 10, 5, 2, 1, 0.50, 0.20, 0.10, 0.05, 0.01};

    @Override
    public ResultDTO analyseSum(InputData previousData, InputData currentData) {
       ResultDTO resultDTO = new ResultDTO();
       
        if (previousData != null) {
            calculateDifference(previousData, currentData, resultDTO);
            
            return resultDTO;
        } 
        
        //else case just result tree
        analyseCurrent(currentData, resultDTO);

        return resultDTO;
    }

    private void calculateDifference(InputData previousData, InputData currentData, ResultDTO resultDTO) {
        double previousSum = previousData.getSumValue();
        double currentSum = currentData.getSumValue();
        

        TreeMap<Double, Integer> previousTreeMap = doSumFitting(previousSum);
        TreeMap<Double, Integer> currentTreeMap = doSumFitting(currentSum);
        

        TreeMap<Double, Integer> differenceTreeMap = doDifferenceSumFitting(currentTreeMap, previousTreeMap);

        resultDTO.setDifferenceTree(differenceTreeMap);
        resultDTO.setResultTree(currentTreeMap);
    }

    private void analyseCurrent(InputData currentData, ResultDTO resultDTO) {
        double currentSum = currentData.getSumValue();
        TreeMap<Double, Integer> currentTreeMap = doSumFitting(currentSum);
        resultDTO.setResultTree(currentTreeMap);
    }

    private TreeMap<Double, Integer> doSumFitting (double inputSum) {
        TreeMap<Double, Integer> sumFitting = new TreeMap<>(Collections.reverseOrder());

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