package dedalus.backend.controller;

import java.net.http.HttpResponse.ResponseInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dedalus.backend.dto.InputDTO;
import dedalus.backend.model.InputData;
import dedalus.backend.service.read.ReadInputService;
import jakarta.validation.Valid;


/**
 * Kommuniziert als Controller-Schnittstelle mit der Datenbank
 * Damit kann auch Angular-Frontend kommunizeren, um die aktuellsten Daten abgreifen und speichern zu können
 */
@RestController
@RequestMapping("/dedalus-api/sumanalyzer")
public class InputDataController {

    @Autowired
    private ReadInputService readInputService;

    @PostMapping("/saveInput")
    public ResponseEntity<Void> saveInput(@Valid @RequestBody InputDTO inputDTO) {
        InputData inputData = readInputService.readAndMapInputDtoToInputData(inputDTO);
        readInputService.saveInputData(inputData);
        return ResponseEntity.ok().build();
    }


    //TODO: CHECK OUT THAT LAST INPUT
    @GetMapping("/lastInput")
    public ResponseEntity<InputData> getLastInput() {
        InputData lastInput = readInputService.getPreviousInput();
        return ResponseEntity.ok(lastInput);
    }

    
}
