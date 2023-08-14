package api.rest.Devsu.ejerciciopractico.service;

import java.util.List;

import api.rest.Devsu.ejerciciopractico.dto.ClienteDTO;
import api.rest.Devsu.ejerciciopractico.model.ClienteModel;

public interface ClienteService {

	public List<ClienteDTO> findClienteAll();

	public ClienteModel createCliente(ClienteDTO cliente);

	public ClienteDTO updateCliente(ClienteDTO cliente);

	public void deleteCliente(Long id);

	public ClienteDTO getCliente(Long id);

	public ClienteDTO getClienteByIdenti(String docIdenti);

}
