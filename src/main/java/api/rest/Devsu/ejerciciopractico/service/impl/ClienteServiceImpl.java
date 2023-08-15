package api.rest.Devsu.ejerciciopractico.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.rest.Devsu.ejerciciopractico.dto.ClienteDTO;
import api.rest.Devsu.ejerciciopractico.model.ClienteModel;
import api.rest.Devsu.ejerciciopractico.repository.ClienteRepository;
import api.rest.Devsu.ejerciciopractico.service.ClienteService;
import api.rest.Devsu.ejerciciopractico.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<ClienteDTO> findClienteAll() {
		// TODO Auto-generated method stub
		List<ClienteModel> clientesModel;
		clientesModel = clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = clientesModel.stream().filter(cliente -> "1".equals(cliente.getEstado()))
				.map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNombres(), cliente.getGenero(),
						cliente.getTipoDoc(), cliente.getNumDoc(), cliente.getDireccion(), cliente.getTelefono(),
						cliente.getContraseña(), cliente.getEstado().equalsIgnoreCase("1") ? "true" : "false"))
				.collect(Collectors.toList());

		return clientesDTO;
	}

	@Override
	public ClienteModel createCliente(ClienteDTO cliente) {
		// TODO Auto-generated method stub
		ClienteModel clientesModel = new ClienteModel();
		clientesModel.setNombres(cliente.getNombres());
		clientesModel.setContraseña(cliente.getContraseña());
		clientesModel.setDireccion(cliente.getDireccion());
		clientesModel.setGenero(cliente.getGenero());
		clientesModel.setTipoDoc(cliente.getTipoDoc());
		clientesModel.setNumDoc(cliente.getNumDoc());
		clientesModel.setTelefono(cliente.getTelefono());
		clientesModel.setEstado(cliente.getEstado().equalsIgnoreCase("true") ? "1" : "0");

		return clienteRepository.saveAndFlush(clientesModel);
	}

	@Override
	public ClienteDTO updateCliente(ClienteDTO cliente) {
		// TODO Auto-generated method stub

		ClienteModel clienteModelIn = new ClienteModel();
		clienteModelIn.setId(cliente.getId());
		clienteModelIn.setNombres(cliente.getNombres());
		clienteModelIn.setContraseña(cliente.getContraseña());
		clienteModelIn.setDireccion(cliente.getDireccion());
		clienteModelIn.setGenero(cliente.getGenero());
		clienteModelIn.setTipoDoc(cliente.getTipoDoc());
		clienteModelIn.setNumDoc(cliente.getNumDoc());
		clienteModelIn.setTelefono(cliente.getTelefono());
		clienteModelIn.setEstado(cliente.getEstado().equalsIgnoreCase("true") ? "1" : "0");

		clienteRepository.save(clienteModelIn);

		return cliente;
	}

	@Override
	public void deleteCliente(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

	@Override
	public ClienteDTO getCliente(Long id) {
		// TODO Auto-generated method stub
		Optional<ClienteModel> clientesModel;
		clientesModel = clienteRepository.findById(id);

		if (clientesModel.isEmpty()) {
			log.info("El cliente ingresado no existe. [{}]",id);			
			throw new ServiceException("El cliente ingresado no existe.");
		}

		List<ClienteDTO> clientesDTO = clientesModel.stream().filter(cliente -> "1".equals(cliente.getEstado()))
				.map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNombres(), cliente.getGenero(),
						cliente.getTipoDoc(), cliente.getNumDoc(), cliente.getDireccion(), cliente.getTelefono(),
						cliente.getContraseña(), cliente.getEstado().equalsIgnoreCase("1") ? "true" : "false"))
				.collect(Collectors.toList());

		return clientesDTO.get(0);
	}

	@Override
	public ClienteDTO getClienteByIdenti(String docIdenti) {
		// TODO Auto-generated method stub
		Optional<ClienteModel> clientesModel;
		clientesModel = clienteRepository.findByNumDoc(docIdenti);

		if (clientesModel.isEmpty()) {
			log.info("El cliente ingresado no existe. [{}]",docIdenti);			
			throw new ServiceException("El cliente ingresado no existe.");
		}

		List<ClienteDTO> clientesDTO = clientesModel.stream().filter(cliente -> "1".equals(cliente.getEstado()))
				.map(cliente -> new ClienteDTO(cliente.getId(), cliente.getNombres(), cliente.getGenero(),
						cliente.getTipoDoc(), cliente.getNumDoc(), cliente.getDireccion(), cliente.getTelefono(),
						cliente.getContraseña(), cliente.getEstado().equalsIgnoreCase("1") ? "true" : "false"))
				.collect(Collectors.toList());

		return clientesDTO.get(0);
	}

}
