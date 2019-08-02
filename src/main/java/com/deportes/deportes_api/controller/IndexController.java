package com.deportes.deportes_api.controller;

import org.apache.log4j.Logger; 
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deportes.deportes_api.repositorios.DeporteRepositorio;
import com.deportes.deportes_api.tablas.Deporte;

@RestController
public class IndexController {
	@Autowired
	DeporteRepositorio repository;
	Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping("/")
	public String index() {
		 Deporte d = new Deporte();
		 d.setVersion(1);
		 d.setNombre("primer ejemplo");
		 repository.save(d);
		 logger.info("guardado");
		 return "Greetings from Spring Boot!";
	}
	 
}
