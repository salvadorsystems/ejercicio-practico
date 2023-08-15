 package api.rest.Devsu.ejerciciopractico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import api.rest.Devsu.ejerciciopractico.dto.ClienteDTO;
import api.rest.Devsu.ejerciciopractico.model.ClienteModel;
import api.rest.Devsu.ejerciciopractico.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteIntegrationTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;
    
    @Test
    public void testCrearYObtenerPedido() throws Exception {
    	ClienteModel clienteModel = new ClienteModel();      	
    	clienteModel.setNombres("SALVADOR");
    	clienteModel.setGenero("MASCULINO");
    	clienteModel.setTipoDoc("DNI");
    	clienteModel.setNumDoc("72521448");
    	clienteModel.setDireccion("CHIMBOTE");
    	clienteModel.setTelefono("931654207");
    	clienteModel.setContraseña("123");
    	clienteModel.setEstado("true");

        Mockito.when(clienteService.createCliente(Mockito.any(ClienteDTO.class)))
               .thenReturn(clienteModel);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombres\": \"SALVADOR\", \"genero\": \"MASCULINO\", \"tipoDoc\": \"DNI\", \"numDoc\": \"73415698\", \"direccion\": \"CHIMBOTE\", \"telefono\": \"931654207\", \"contraseña\": \"123\", \"estado\": true}"))
                .andExpect(status().isOk());

    }

}
