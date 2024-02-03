package com.intercorp.ms1.MS01.configuration.kafka;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.intercorp.ms1.MS01.controller.RootController;
import com.intercorp.ms1.MS01.dto.RootDTO;
import com.intercorp.ms1.MS01.model.Person;
import com.intercorp.ms1.MS01.model.Root2;
import com.intercorp.ms1.MS01.service.RootService;

@Service
public class JsonRootConsumer {
	
	@Autowired
	RootService rootService;

	@KafkaListener(topics = "Kafka-01")
	public void consume(List<LinkedHashMap<String, Object>> messages) {
		//List<RootDTO> listaRoos = new ArrayList<>();
	    for (LinkedHashMap<String, Object> message : messages) {	        
	    	RootDTO rootDTO = convertToRoot2(message);
	        //listaRoos.add(rootDTO);
	        rootService.createRoot(rootDTO);
	    }	   	    
	}
	
	private RootDTO convertToRoot2(LinkedHashMap<String, Object> message) {
		RootDTO rootDto = new RootDTO();
	
		//root2.setRoot_id(0);
		rootDto.setPerson(convertToRoot3((LinkedHashMap<String, Object>) message.get("person")));
		rootDto.setRandom((int) message.get("random"));
		rootDto.setRandom_float((double)message.get("random_float"));
		rootDto.setBool((boolean) message.get("bool"));  		
		long timestamp = (Long) message.get("date"); // Puedes obtener el tiempo actual en milisegundos
        // Convertir el long a un objeto Date
        Date date = new Date(timestamp);
		
        rootDto.setDate((Date) date);
        rootDto.setRegEx((String) message.get("regEx"));
        rootDto.setEnumValue((String) message.get("enum"));        
        rootDto.setElt((List<String>)message.get("elt"));
        rootDto.setAge((int)message.get("age"));

        return rootDto;
	}

	private Person convertToRoot3(LinkedHashMap<String, Object> message) {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setFirstname((String) message.get("firstname"));
		person.setLastname((String) message.get("lastname"));
		person.setCity((String) message.get("city"));
		person.setCountry((String) message.get("country"));
		person.setFirstname2((String) message.get("firstname2"));
		person.setLastname2((String) message.get("lastname2"));
		person.setEmail((String) message.get("email"));
		return person;
		
		
	}

	
}

