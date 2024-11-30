package dedalus.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputDTO {

    @NotNull (message = "Null is not allowed")
    @Positive (message = "Value must be positive")
    private double value;

}
