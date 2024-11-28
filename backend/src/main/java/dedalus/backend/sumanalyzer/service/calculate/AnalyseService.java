package dedalus.backend.sumanalyzer.service.calculate;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.dto.ResultDTO;

public interface AnalyseService {
    ResultDTO analyseSum(InputDTO inputDTO);
}