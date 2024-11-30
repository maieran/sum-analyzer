package dedalus.backend.service.read;

import dedalus.backend.dto.InputDTO;

public interface ReadInputService {
    void read(InputDTO inputDTO);
    InputDTO getPreviousInput(); // Liefert den vorherigen Input zurück

}