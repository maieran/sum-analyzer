package dedalus.backend.service.analyse;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.dto.ResultDTO;

public interface AnalyseService {
    ResultDTO analyseSum(InputDTO inputDTO);
}