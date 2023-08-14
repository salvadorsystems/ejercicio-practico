package api.rest.Devsu.ejerciciopractico.controller;

import java.util.ArrayList;
import java.util.List;

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

import api.rest.Devsu.ejerciciopractico.dto.CuentaDTO;
import api.rest.Devsu.ejerciciopractico.model.CuentaModel;
import api.rest.Devsu.ejerciciopractico.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<CuentaDTO>> listar() {
		List<CuentaDTO> cuentas = new ArrayList<>();
		cuentas = cuentaService.findCuentaAll();
		return new ResponseEntity<List<CuentaDTO>>(cuentas, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CuentaDTO> crearCuenta(@Valid @RequestBody CuentaDTO cuenta) {
		cuentaService.createCuenta(cuenta);
		return new ResponseEntity<CuentaDTO>(HttpStatus.CREATED);
	}

	@GetMapping("numDoc/{numDoc}")
	public ResponseEntity<List<CuentaDTO>> getCuentaByCliente(@PathVariable("numDoc") String numDoc) {
		List<CuentaDTO> cuenta = cuentaService.getCuentaByCliente(numDoc);
		return new ResponseEntity<List<CuentaDTO>>(cuenta, HttpStatus.OK);
	}

	@GetMapping("numCuenta/{numCuenta}")
	public ResponseEntity<List<CuentaDTO>> getCuentaByNumCuenta(@PathVariable("numCuenta") String numCuenta) {
		List<CuentaDTO> cuenta = cuentaService.getCuentaBynumCuenta(numCuenta);
		return new ResponseEntity<List<CuentaDTO>>(cuenta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CuentaModel> deleteCuenta(@PathVariable("id") Long id) {
		cuentaService.deleteCuenta(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public CuentaDTO updateCuenta(@PathVariable("id") Long id, @Valid @RequestBody CuentaDTO cuenta) {

		CuentaDTO dCuenta = cuentaService.getCuenta(id);
		dCuenta.setNumCuenta(cuenta.getNumCuenta());
		dCuenta.setEstado(cuenta.getEstado());
		dCuenta.setTipCuenta(cuenta.getTipCuenta());
		dCuenta.setSaldoInit(cuenta.getSaldoInit());
		dCuenta.setClienteDTO(cuenta.getClienteDTO());

		return cuentaService.updateCuenta(dCuenta);
	}

}
