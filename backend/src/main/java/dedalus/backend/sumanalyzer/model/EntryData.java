package dedalus.backend.sumanalyzer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 
 */
@Data
@Entity
public class EntryData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double entryValue;

    public EntryData(double entryValue) {
        this.entryValue = entryValue;
    }


}
