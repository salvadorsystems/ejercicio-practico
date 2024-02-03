package com.intercorp.ms1.MS01.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Root2")
@Table(name = "Root")
public class Root2 {
		
	  	@Id
		@Column(name = "root_id")
	  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRoot")
		@SequenceGenerator(sequenceName = "root_seq", allocationSize = 1, name = "seqRoot")	
		@Builder.Default
	    private Long id = 0L;

	  	@ManyToOne
	  	@JoinColumn(name = "person_id")
	  	private Person person;

	    @Column(name = "random")
	    private int random;

	    @Column(name = "random_float")
	    private double random_float;

	    @Column(name = "BOOL_VALUE")
	    private boolean bool;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "DATE_VALUE")
	    private Date date;

	    @Column(name = "REG_EX")
	    private String regEx;	    	    
	    
	    @Column(name = "enum_value")
	    @JsonProperty("enum")
	    private String enumValue;

	    @Column(name = "elt")	    
	    private String elt;

	    @Column(name = "age")
	    private int age;	    
	    
	    @PrePersist
		private void setfechaRegistro() {
	    	fechaRegistro = LocalDateTime.now();
		}	    
	    
		@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
		@Column(name = "FECHA_REGISTRO")
		private LocalDateTime fechaRegistro;
	      
}
