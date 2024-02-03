package com.intercorp.ms1.MS01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Person")
@Table(name = "Person")
public class Person {
		
		@Id
		@Column(name = "PERSON_ID")
	  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPerson")
		@SequenceGenerator(sequenceName = "person_seq", allocationSize = 1, name = "seqPerson")	
		@Builder.Default
		private Long id = 0L;
		
		@Column(name = "firstname")
	    private String firstname;		
		
		@Column(name = "lastname")
	    private String lastname;	
		
		@Column(name = "city")
	    private String city;	  
		
		@Column(name = "country")
	    private String country;
		
		@Column(name = "firstname2")
	    private String firstname2;
		
		@Column(name = "lastname2")
	    private String lastname2;
		
		@Column(name = "email")
	    private String email;		

}
