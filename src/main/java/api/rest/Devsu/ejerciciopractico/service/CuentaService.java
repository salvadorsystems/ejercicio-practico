package api.rest.Devsu.ejerciciopractico.service;

import java.util.List;
import api.rest.Devsu.ejerciciopractico.dto.CuentaDTO;
import api.rest.Devsu.ejerciciopractico.model.CuentaModel;

public interface CuentaService {

	public List<CuentaDTO> findCuentaAll();

	public CuentaModel createCuenta(CuentaDTO cuenta);

	public CuentaDTO updateCuenta(CuentaDTO cuenta);

	public void deleteCuenta(Long id);

	public CuentaDTO getCuenta(Long id);

	public List<CuentaDTO> getCuentaByCliente(String docIdenti);

	public List<CuentaDTO> getCuentaBynumCuenta(String numCuenta);

}
