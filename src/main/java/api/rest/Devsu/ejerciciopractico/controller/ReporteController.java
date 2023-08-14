package api.rest.Devsu.ejerciciopractico.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.rest.Devsu.ejerciciopractico.dto.ReporteDTO;
import api.rest.Devsu.ejerciciopractico.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private ReporteService reporteService;

	@GetMapping()
	public ResponseEntity<List<ReporteDTO>> getReporte(
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaInicio,
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date fechaFin, @RequestParam String numDoc) {
		List<ReporteDTO> reportes = new ArrayList<>();
		Instant instantIni = fechaInicio.toInstant();
		Instant instantFin = fechaFin.toInstant();
		reportes = reporteService.getReporteByFechaCuenta_numDoc(
				instantIni.atZone(ZoneId.systemDefault()).toLocalDateTime(),
				instantFin.atZone(ZoneId.systemDefault()).toLocalDateTime(), numDoc);
		return new ResponseEntity<List<ReporteDTO>>(reportes, HttpStatus.OK);
	}

}
