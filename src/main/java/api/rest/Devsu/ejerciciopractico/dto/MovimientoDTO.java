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
public class MovimientoDTO {

	private Long id;
	private LocalDateTime fecha;
	private String tipMov;
	private double valor;
	private double saldoDisponible;
	private CuentaDTO cuentaDTO;
	
}
