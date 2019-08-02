package com.deportes.deportes_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deportes.deportes_api.repositorios.DeporteRepositorio;
import com.deportes.deportes_api.tablas.Deporte;

@RestController
public class IndexController {
	@Autowired
	DeporteRepositorio repository;
	final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
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
