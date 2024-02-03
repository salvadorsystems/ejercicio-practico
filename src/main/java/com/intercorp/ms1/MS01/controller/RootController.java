package com.intercorp.ms1.MS01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.ms1.MS01.dto.RootDTO;
import com.intercorp.ms1.MS01.service.RootService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/root")
public class RootController {	
	
	@Autowired
	RootService rootService;
	
	@PostMapping
	public Object crearRoot(@Validated @RequestBody RootDTO root, BindingResult result) {
		if(result.hasErrors()) {
			log.info("errores",result.getAllErrors());
			return result.getAllErrors();
		}		
		return rootService.createRoot(root);		
	}


}
