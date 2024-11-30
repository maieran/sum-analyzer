package dedalus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.TreeMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    
    //private TreeMap<Double, Integer> resultTree;
    double outputValue;

}