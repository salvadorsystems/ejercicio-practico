package api.rest.Devsu.ejerciciopractico.service;


import java.util.List;

import api.rest.Devsu.ejerciciopractico.dto.MovimientoDTO;
import api.rest.Devsu.ejerciciopractico.model.MovimientoModel;

public interface MovimientoService {
	
    public List<MovimientoDTO> findMovimientoAll();
    public MovimientoModel createMovimiento(MovimientoDTO movimiento);
    public MovimientoDTO updateMovimiento(MovimientoDTO movimiento);
    public void deleteMovimiento(Long id);
    public MovimientoDTO getMovimiento(Long id);   
    public List<MovimientoDTO> getMovimientoByNumDoc(String numDoc);        

}
