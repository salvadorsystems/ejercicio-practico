package api.rest.Devsu.ejerciciopractico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {

	private Long id;
	private String numCuenta;
	private String tipCuenta;
	private double saldoInit;
	private String estado;
	private ClienteDTO clienteDTO;
}
