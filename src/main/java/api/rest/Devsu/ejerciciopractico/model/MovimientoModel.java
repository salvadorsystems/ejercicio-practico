package api.rest.Devsu.ejerciciopractico.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MovimientoModel")
@Data
@Table(name = "MOVIMIENTOS")
public class MovimientoModel {

	@Id
	@Column(name = "ID_MOVIMIENTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMovimientos")
	@SequenceGenerator(sequenceName = "SEQ_MOVIMIENTOS", allocationSize = 1, name = "seqMovimientos")
	@Builder.Default
	private Long id = 0L;

	@PrePersist
	private void setfechaRegistro() {
		fecha = LocalDateTime.now();
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "FECHA_MOV")
	private LocalDateTime fecha;

	@NotNull(message = "El tipo de Movimiento es necesario")
	@Size(min = 3, max = 50, message = "El tipo de Movimiento debe tener como minimo {min} y como maximo {max} caracteres")
	@Column(name = "TIP_MOV")
	private String tipMov;

	@Positive(message = "El valor debe ser positivo")
	@Column(name = "VALOR")
	private double valor;

	@PositiveOrZero(message = "El saldo deber ser positivo o cero")
	@Column(name = "SALDO")
	private double saldoDisponible;

	@ManyToOne
	@JoinColumn(name = "ID_CUENTA", nullable = false)
	private CuentaModel cuentaModel;

}
