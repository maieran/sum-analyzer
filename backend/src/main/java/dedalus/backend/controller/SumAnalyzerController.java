package dedalus.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.dto.ResultDTO;
import dedalus.backend.service.analyse.AnalyseService;
import dedalus.backend.service.read.ReadInputService;
import dedalus.backend.service.write.WriteResultService;

@RestController
@RequestMapping("/dedalus-api/sumanalyzer")
public class SumAnalyzerController {

    @Autowired(required=true)
    private AnalyseService analyseService;

    @Autowired(required=true)
    private ReadInputService readInputService;

    @Autowired(required=true)
    private WriteResultService writeResultService;


    @PostMapping("/analyseSum")
    public ResponseEntity<ResultDTO> generateSumAnalysis(@RequestBody InputDTO inputDTO) {

        if (inputDTO == null) {
        }

        readInputService.read(inputDTO);
        ResultDTO analysisResult = analyseService.analyseSum(inputDTO);
        writeResultService.write(analysisResult);

        return ResponseEntity.ok(analysisResult);
    }

    
}