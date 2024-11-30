package dedalus.backend.service.analyse;

import dedalus.backend.dto.ResultDTO;
import dedalus.backend.model.InputData;

public interface AnalyseService {
    ResultDTO analyseSum(InputData previousData , InputData currentData);
}