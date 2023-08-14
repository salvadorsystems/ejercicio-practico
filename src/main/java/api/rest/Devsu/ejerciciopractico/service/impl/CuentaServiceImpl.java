package api.rest.Devsu.ejerciciopractico.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.rest.Devsu.ejerciciopractico.dto.ClienteDTO;
import api.rest.Devsu.ejerciciopractico.dto.CuentaDTO;
import api.rest.Devsu.ejerciciopractico.model.ClienteModel;
import api.rest.Devsu.ejerciciopractico.model.CuentaModel;
import api.rest.Devsu.ejerciciopractico.repository.ClienteRepository;
import api.rest.Devsu.ejerciciopractico.repository.CuentaRepository;
import api.rest.Devsu.ejerciciopractico.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService{

	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<CuentaDTO> findCuentaAll() {
		// TODO Auto-generated method stub
		List<CuentaModel> cuentasModel = cuentaRepository.findAll();	
		List<CuentaDTO> cuentas = mapToCuentaDTO(cuentasModel);
		
		return cuentas;
	}

	@Override
	public CuentaModel createCuenta(CuentaDTO cuenta) {
		// TODO Auto-generated method stub	
		CuentaModel cuentas = mapToCuentaModel(cuenta);			
		return cuentaRepository.saveAndFlush(cuentas);
	}

	@Override
	public CuentaDTO updateCuenta(CuentaDTO Cuenta) {
		// TODO Auto-generated method stub
		
		CuentaModel cuentaModel = mapToCuentaModel(Cuenta);
		
		cuentaRepository.save(cuentaModel);
		Cuenta = getCuenta(cuentaModel.getId());
		return Cuenta;
	}

	@Override
	public void deleteCuenta(Long id) {
		// TODO Auto-generated method stub
		cuentaRepository.deleteById(id);
	}

	@Override
	public CuentaDTO getCuenta(Long id) {
		// TODO Auto-generated method stub
		
		List<CuentaModel> lstCuentaModel = new ArrayList<>();
		Optional<CuentaModel> cuentaModel = cuentaRepository.findById(id);
		lstCuentaModel.add(cuentaModel.get());
		List<CuentaDTO> cuenta = mapToCuentaDTO(lstCuentaModel);
		
		return cuenta.get(0);
	}
	
	public List<CuentaDTO> mapToCuentaDTO(List<CuentaModel> cuentasModel) {
		List<CuentaDTO>  cuentasDTO = new ArrayList<>();
		for (CuentaModel cuentaModel2 : cuentasModel) {
			CuentaDTO cuentaDTO = new CuentaDTO();
			cuentaDTO.setId(cuentaModel2.getId());
			cuentaDTO.setTipCuenta(cuentaModel2.getTipCuenta());
			cuentaDTO.setNumCuenta(cuentaModel2.getNumCuenta());
			cuentaDTO.setSaldoInit(cuentaModel2.getSaldoInit());
			cuentaDTO.setEstado(cuentaModel2.getEstado().equalsIgnoreCase("1") ? "true" : "false");	
	        if (cuentaModel2.getClienteModel() != null) {
	        	cuentaDTO.setClienteDTO(mapToClienteDTO(cuentaModel2.getClienteModel()));
	        }     
	        cuentasDTO.add(cuentaDTO);
		}
					
        return cuentasDTO;
    }
	
	public CuentaModel mapToCuentaModel(CuentaDTO cuentaDTO) {
			
			CuentaModel cuentaModel = new CuentaModel();
												
				cuentaModel.setId(cuentaDTO.getId());
				cuentaModel.setTipCuenta(cuentaDTO.getTipCuenta());
				cuentaModel.setNumCuenta(cuentaDTO.getNumCuenta());
				cuentaModel.setSaldoInit(cuentaDTO.getSaldoInit());
				cuentaModel.setEstado(cuentaDTO.getEstado().equalsIgnoreCase("true") ? "1" : "0");	
		        if (cuentaDTO.getClienteDTO() != null) {
		        	cuentaModel.setClienteModel(mapToClienteModel(cuentaDTO.getClienteDTO()));
		        }     		       		
									
        return cuentaModel;
    }

    private ClienteDTO mapToClienteDTO(ClienteModel clienteModel) {
    	ClienteDTO clienteDTO = new ClienteDTO();
    	clienteDTO.setId(clienteModel.getId());
    	clienteDTO.setNombres(clienteModel.getNombres());
    	clienteDTO.setTipoDoc(clienteModel.getTipoDoc());
    	clienteDTO.setNumDoc(clienteModel.getNumDoc());
    	clienteDTO.setContraseña(clienteModel.getContraseña());
    	clienteDTO.setDireccion(clienteModel.getDireccion());
    	clienteDTO.setGenero(clienteModel.getGenero());
    	clienteDTO.setTelefono(clienteModel.getTelefono());
    	clienteDTO.setEstado(clienteModel.getEstado().equalsIgnoreCase("1") ? "true" : "false");
        return clienteDTO;
    }
    
    private ClienteModel mapToClienteModel(ClienteDTO clienteDTO) {    	
    	String numDoc  = clienteDTO.getNumDoc();
    	Optional<ClienteModel> clienteModel= clienteRepository.findByNumDoc(numDoc);

        return clienteModel.get();
    }

	@Override
	public List<CuentaDTO>  getCuentaByCliente(String docIdenti) {
		// TODO Auto-generated method stub
		List<CuentaModel> cuentasModel = cuentaRepository.findByClienteModel_NumDoc(docIdenti);
		List<CuentaDTO>  cuentasDTO = mapToCuentaDTO(cuentasModel);
				
		return cuentasDTO;
	}

	@Override
	public List<CuentaDTO> getCuentaBynumCuenta(String numCuenta) {
		// TODO Auto-generated method stub
		List<CuentaModel> cuentasModel = cuentaRepository.findByNumCuenta(numCuenta);
		List<CuentaDTO>  cuentasDTO = mapToCuentaDTO(cuentasModel);
				
		return cuentasDTO;
	}



}
