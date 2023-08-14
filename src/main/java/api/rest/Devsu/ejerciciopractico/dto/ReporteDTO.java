package api.rest.Devsu.ejerciciopractico.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

	private LocalDateTime fecha;
	private String cliente;
	private String numCuenta;
	private String tipCuenta;
	private double saldoInit;
	private String estado;
	private String movimiento;
	private double saldoDisponible;

}
