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

import api.rest.Devsu.ejerciciopractico.dto.MovimientoDTO;
import api.rest.Devsu.ejerciciopractico.model.MovimientoModel;
import api.rest.Devsu.ejerciciopractico.service.MovimientoService;

@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<MovimientoDTO>> listar() {
		List<MovimientoDTO> movimientos = new ArrayList<>();
		movimientos = movimientoService.findMovimientoAll();
		return new ResponseEntity<List<MovimientoDTO>>(movimientos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MovimientoDTO> crearMovimiento(@Valid @RequestBody MovimientoDTO movimiento) {
		movimientoService.createMovimiento(movimiento);
		return new ResponseEntity<MovimientoDTO>(HttpStatus.CREATED);
	}

	@GetMapping("numDoc/{numDoc}")
	public ResponseEntity<List<MovimientoDTO>> getMovimientoByNumDoc(@PathVariable("numDoc") String numDoc) {
		List<MovimientoDTO> movimiento = movimientoService.getMovimientoByNumDoc(numDoc);
		return new ResponseEntity<List<MovimientoDTO>>(movimiento, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MovimientoModel> deleteMovimiento(@PathVariable("id") Long id) {
		movimientoService.deleteMovimiento(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public MovimientoDTO updateMovimiento(@PathVariable("id") Long id, @Valid @RequestBody MovimientoDTO movimiento) {

		MovimientoDTO dMovimiento = movimientoService.getMovimiento(id);
		dMovimiento.setTipMov(movimiento.getTipMov());
		dMovimiento.setFecha(movimiento.getFecha());
		dMovimiento.setValor(movimiento.getValor());
		dMovimiento.setSaldoDisponible(movimiento.getSaldoDisponible());
		dMovimiento.setCuentaDTO(movimiento.getCuentaDTO());

		return movimientoService.updateMovimiento(dMovimiento);
	}

}
