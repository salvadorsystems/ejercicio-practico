package api.rest.Devsu.ejerciciopractico.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Data
public class PersonaModel {
	
	@NotNull(message = "EL nombre del cliente es necesario")
	@Size(min = 3, max = 120, message = "El nombre debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "NOMBRES")
	private String nombres;
	
	@NotNull(message = "El género es necesario")
	@Size(min = 1, max = 20, message = "El genero debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "GENERO")
	private String genero;
	
	@NotNull(message = "Es necesario ingresar el tipo de documento - DNI, Pasaporte, Libreta Electoral, etc.")
	@Size(min = 1, max = 20, message = "La identificacion debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "TIPO_DOC")
	private String tipoDoc;
	
	@NotNull(message = "Es necesario ingresar una numero de documento - DNI, Pasaporte, Libreta Electoral, etc.")
	@Size(min = 1, max = 20, message = "La identificacion debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "NUM_DOC")
	private String numDoc;
	
	@NotNull(message = "Es necesario ingresar una direccion o referencia")
	@Size(min = 1, max = 200, message = "La direccion debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "DIRECCION")
	private String direccion;
	
	@NotNull(message = "Es necesario ingresar un telefono")
	@Size(min = 3, max = 50, message = "El telefono  debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "TELEFONO")
	private String telefono;

}
