package com.intercorp.ms1.MS01.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intercorp.ms1.MS01.model.Person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RootDTO {


    private Long id = 0L;

  	private Person person;
   
    private int random;
   
    private double random_float;
    
    private boolean bool;

    private Date date;
   
    private String regEx;
    
    @JsonProperty("enum")
    private String enumValue;
    
    @JsonProperty("elt")
    private List<String> elt;
    
    private int age;
	
}
