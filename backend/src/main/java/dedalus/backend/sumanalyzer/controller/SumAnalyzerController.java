package dedalus.backend.sumanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dedalus.backend.sumanalyzer.service.write.WriteResultService;
import dedalus.backend.dto.InputDTO;
import dedalus.backend.dto.ResultDTO;
import dedalus.backend.sumanalyzer.service.calculate.AnalyseService;

@RestController
@RequestMapping("/dedalus-api/sumanalyzer")
public class SumAnalyzerController {

    @Autowired(required=true)
    private AnalyseService analyseService;

/*     @Autowired(required=true)
    private ReadInputService readInputService; */

    @Autowired(required=true)
    private WriteResultService writeResultService;


    @PostMapping("/analyseSum")
    public ResponseEntity<ResultDTO> generateSumAnalysis(@RequestBody InputDTO inputDTO) {
        if (inputDTO == null) {
        }

        ResultDTO analysisResult = analyseService.analyseSum(inputDTO);
        writeResultService.write(analysisResult);

        return ResponseEntity.ok(analysisResult);
    }

    
}
