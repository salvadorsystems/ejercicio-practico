package api.rest.Devsu.ejerciciopractico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.rest.Devsu.ejerciciopractico.dto.ClienteDTO;
import api.rest.Devsu.ejerciciopractico.service.ClienteService;

@SpringBootTest
class EjercicioPracticoApplicationTests {

	@Autowired
    private ClienteService clienteService;
	
	@Test
	void getClienteByIdenti() {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(41L);
		clienteDTO.setNombres("DANIELA ALEJANDRA LEIVA DEZA");
		clienteDTO.setGenero("FEMENINA");
		clienteDTO.setTipoDoc("DNI");
		clienteDTO.setNumDoc("73415697");
		clienteDTO.setDireccion("CHIMBOTE-CAMINO REAL");
		clienteDTO.setTelefono("923224324");
		clienteDTO.setContraseña("123");
		clienteDTO.setEstado("true");
		ClienteDTO clienteDTOResult = clienteService.getClienteByIdenti("73415697");
		assertEquals(clienteDTO.getId(), clienteDTOResult.getId());
	}

}
