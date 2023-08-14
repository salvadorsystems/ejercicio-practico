package api.rest.Devsu.ejerciciopractico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

	private Long id;

	private String nombres;

	private String genero;

	private String tipoDoc;

	private String numDoc;

	private String direccion;

	private String telefono;

	private String contraseña;

	private String estado;

	@Override
	public String toString() {
		return "ClienteDTO [id=" + id + ", nombres=" + nombres + ", genero=" + genero + ", tipoDoc=" + tipoDoc
				+ ", numDoc=" + numDoc + ", direccion=" + direccion + ", telefono=" + telefono + ", contraseña="
				+ contraseña + ", estado=" + estado + "]";
	}

}
