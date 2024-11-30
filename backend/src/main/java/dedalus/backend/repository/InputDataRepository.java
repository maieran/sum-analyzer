package dedalus.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dedalus.backend.model.InputData;

@Repository
public interface InputDataRepository extends JpaRepository<InputData, Long> {
    
    @Query("SELECT input FROM InputData input ORDER BY input.id DESC")
    Optional<InputData> findLatestInput();
}