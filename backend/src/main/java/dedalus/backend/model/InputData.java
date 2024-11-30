package dedalus.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Unsere Modell-Klasse, von wo aus auch Daten persistieren können
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double sumValue;

    private LocalDateTime timestamp;
}
