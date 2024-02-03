package com.intercorp.ms1.MS01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.ms1.MS01.dto.RootDTO;
import com.intercorp.ms1.MS01.model.Person;
import com.intercorp.ms1.MS01.model.Root2;
import com.intercorp.ms1.MS01.repository.PersonRepository;
import com.intercorp.ms1.MS01.repository.RootRepository;

@Service
public class RootService {
	
	@Autowired
	private RootRepository rootRepository;
	
	@Autowired
	private PersonRepository personRepository;
		
	@Autowired
	private ObjectMapper om;
	
	public RootDTO createRoot(RootDTO rootDTO) {
		
		
		Person person = new Person();
		person.setCity(rootDTO.getPerson().getCity());
		person.setCountry(rootDTO.getPerson().getCity());
		person.setEmail(rootDTO.getPerson().getEmail());
		person.setFirstname(rootDTO.getPerson().getFirstname());
		person.setFirstname2(rootDTO.getPerson().getFirstname2());
		person.setLastname(rootDTO.getPerson().getLastname());
		person.setLastname2(rootDTO.getPerson().getLastname2());
		
		person = personRepository.save(person);		
		
		Root2 root2 = new Root2();		
		root2.setPerson(person);
		root2.setRandom(rootDTO.getRandom());
		root2.setRandom_float(rootDTO.getRandom_float());
		root2.setBool(rootDTO.isBool());
		root2.setDate(rootDTO.getDate());
		root2.setRegEx(rootDTO.getRegEx());
		root2.setEnumValue(rootDTO.getEnumValue());				
		//ObjectMapper objectMapper = new ObjectMapper();
		try {
			root2.setElt(om.writeValueAsString(rootDTO.getElt()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root2.setAge(rootDTO.getAge());
		Root2 root = rootRepository.save(root2);
										
		return rootDTO;
	}
	
	
		
}
