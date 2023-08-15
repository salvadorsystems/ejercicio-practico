package api.rest.Devsu.ejerciciopractico.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.rest.Devsu.ejerciciopractico.dto.ReporteDTO;
import api.rest.Devsu.ejerciciopractico.model.MovimientoModel;
import api.rest.Devsu.ejerciciopractico.repository.MovimientoRepository;
import api.rest.Devsu.ejerciciopractico.service.ReporteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
	private MovimientoRepository movimientoRepository;

	@Override
	public List<ReporteDTO> getReporteByFechaCuenta_numDoc(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			String numDoc) {
		// TODO Auto-generated method stub
		log.info("Reporte del cliente. [{}]",numDoc);
		List<MovimientoModel> movimientos = new ArrayList<>();
		movimientos = movimientoRepository.findByFechaBetweenAndCuentaModel_ClienteModel_NumDoc(fechaInicio, fechaFin,
				numDoc);

		// Mepear los movimientos Model a reporte dto - Filtrar y obtener el último
		// movimiento de cada tipo de cuenta
		List<ReporteDTO> ultimosMovimientosPorTipo = movimientos.stream()
				.map(movimiento -> new ReporteDTO(movimiento.getFecha(),
						movimiento.getCuentaModel().getClienteModel().getNombres(),
						movimiento.getCuentaModel().getNumCuenta(), 
						movimiento.getCuentaModel().getTipCuenta(),
						movimiento.getTipMov().equalsIgnoreCase("Deposito")
								? movimiento.getCuentaModel().getSaldoInit() - movimiento.getValor()
								: movimiento.getCuentaModel().getSaldoInit() + movimiento.getValor(),
						!movimiento.getCuentaModel().getEstado().equals("0") ? "true" : "false",
						movimiento.getTipMov().equalsIgnoreCase("Deposito") ? "" + movimiento.getValor()
								: "-" + movimiento.getValor(),
						movimiento.getTipMov().equalsIgnoreCase("Deposito")
								? movimiento.getSaldoDisponible() + movimiento.getValor()
								: movimiento.getSaldoDisponible() - movimiento.getValor()))

				.collect(Collectors.toMap(ReporteDTO::getNumCuenta, movimiento -> movimiento,
						(movimientoExistente, nuevoMovimiento) -> nuevoMovimiento.getFecha()
								.isAfter(movimientoExistente.getFecha()) ? nuevoMovimiento : movimientoExistente))
				.values().stream().collect(Collectors.toList());

		return ultimosMovimientosPorTipo;
	}
}
