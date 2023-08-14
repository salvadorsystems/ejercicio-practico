package api.rest.Devsu.ejerciciopractico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ClienteModel")
@Data
@Table(name = "CLIENTE")
public class ClienteModel extends PersonaModel{
	
	@Id
	@Column(name = "ID_CLIENTE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
	@SequenceGenerator(sequenceName = "SEQ_CLIENTE", allocationSize = 1, name = "seqCliente")
	@Builder.Default
	private Long id = 0L;
	
	@NotNull(message = "La contraseña del cliente es necesario")
	@Size(min = 3, max = 20, message = "La contraseña debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	
	@Column(name = "ESTADO")
	@Builder.Default
	private String estado="1";
	
}
