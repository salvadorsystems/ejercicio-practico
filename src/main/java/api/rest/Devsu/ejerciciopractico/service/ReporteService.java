package api.rest.Devsu.ejerciciopractico.service;

import java.time.LocalDateTime;
import java.util.List;
import api.rest.Devsu.ejerciciopractico.dto.ReporteDTO;

public interface ReporteService {
	        
    public List<ReporteDTO> getReporteByFechaCuenta_numDoc(LocalDateTime fechaInicio, LocalDateTime fechaFin, String numDoc);

}
