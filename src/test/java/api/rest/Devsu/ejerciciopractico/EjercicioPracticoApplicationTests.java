package api.rest.Devsu.ejerciciopractico;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.rest.Devsu.ejerciciopractico.dto.ClienteDTO;
import api.rest.Devsu.ejerciciopractico.dto.CuentaDTO;
import api.rest.Devsu.ejerciciopractico.service.ClienteService;
import api.rest.Devsu.ejerciciopractico.service.CuentaService;

@SpringBootTest
class EjercicioPracticoApplicationTests {

	@Autowired
    private ClienteService clienteService;
	@Autowired
    private CuentaService cuentaService;
	
	@Test
	void getClienteByIdenti() {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(61L);
		clienteDTO.setNombres("MARCO SALVADOR");
		clienteDTO.setGenero("MASCULINO");
		clienteDTO.setTipoDoc("DNI");
		clienteDTO.setNumDoc("72521448");
		clienteDTO.setDireccion("CHIMBOTE-CAMINO REAL");
		clienteDTO.setTelefono("931654207");
		clienteDTO.setContraseña("123");
		clienteDTO.setEstado("true");
		ClienteDTO clienteDTOResult = clienteService.getClienteByIdenti("72521448");
		assertEquals(clienteDTO.getId(), clienteDTOResult.getId());
	}
	
	@Test
	void getCuentaByCliente() {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(61L);
		clienteDTO.setNombres("MARCO SALVADOR");
		clienteDTO.setGenero("MASCULINO");
		clienteDTO.setTipoDoc("DNI");
		clienteDTO.setNumDoc("72521448");
		clienteDTO.setDireccion("CHIMBOTE-CAMINO REAL");
		clienteDTO.setTelefono("931654207");
		clienteDTO.setContraseña("123");
		clienteDTO.setEstado("true");
		
		CuentaDTO cuentaDTO = new CuentaDTO();
		cuentaDTO.setId(21L);
		cuentaDTO.setNumCuenta("987654321");
		cuentaDTO.setTipCuenta("CUENTA AHORRO - VISA");
		cuentaDTO.setSaldoInit(100);
		cuentaDTO.setEstado("true");
		cuentaDTO.setClienteDTO(clienteDTO);
		
		CuentaDTO cuentaDTOResp = cuentaService.getCuenta(21L);
		
		assertEquals(cuentaDTOResp.getSaldoInit(), cuentaDTO.getSaldoInit());
	}

}
