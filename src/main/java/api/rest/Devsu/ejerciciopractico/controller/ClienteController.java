package api.rest.Devsu.ejerciciopractico.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.rest.Devsu.ejerciciopractico.dto.ClienteDTO;
import api.rest.Devsu.ejerciciopractico.model.ClienteModel;
import api.rest.Devsu.ejerciciopractico.service.ClienteService;
import api.rest.Devsu.ejerciciopractico.service.exception.ServiceException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ClienteDTO>> listar() {
		List<ClienteDTO> clientes = new ArrayList<>();
		clientes = clienteService.findClienteAll();
		return new ResponseEntity<List<ClienteDTO>>(clientes, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO cliente) {

		clienteService.createCliente(cliente);
		return new ResponseEntity<ClienteDTO>(HttpStatus.CREATED);
	}

	@GetMapping("/{docIdenti}")
	public ResponseEntity<ClienteDTO> getClienteByIdenti(@PathVariable("docIdenti") String docIdenti) {
		ClienteDTO cliente = clienteService.getClienteByIdenti(docIdenti);

		Optional<ClienteDTO> optionalClienteDTO = Optional.ofNullable(cliente);

		if (!optionalClienteDTO.isPresent()) {
			throw new ServiceException("El Cliente no existe.");
		}

		return new ResponseEntity<ClienteDTO>(cliente, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteModel> deleteCliente(@PathVariable("id") Long id) {
		clienteService.deleteCliente(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ClienteDTO updateCliente(@PathVariable("id") Long id, @Valid @RequestBody ClienteDTO cliente) {

		ClienteDTO clienteDTO = clienteService.getCliente(id);
		clienteDTO.setNombres(cliente.getNombres());
		clienteDTO.setGenero(cliente.getGenero());
		clienteDTO.setTipoDoc(cliente.getTipoDoc());
		clienteDTO.setNumDoc(cliente.getNumDoc());
		clienteDTO.setDireccion(cliente.getDireccion());
		clienteDTO.setTelefono(cliente.getTelefono());
		clienteDTO.setContraseña(cliente.getContraseña());
		clienteDTO.setEstado(cliente.getEstado());

		return clienteService.updateCliente(clienteDTO);

	}

}
