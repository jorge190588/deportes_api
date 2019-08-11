package com.deportes.deportes_api.controller;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.deportes.deportes_api.repositorios.DeporteRepositorio;
import com.deportes.deportes_api.tablas.*;

@RestController
@RequestMapping("deporte")
public class DeporteController {
	@Autowired
	DeporteRepositorio repository;
	Logger logger = Logger.getLogger(DeporteController.class);
	
	@GetMapping("/finbyid/{id}")
	public @ResponseBody  Optional<Deporte> finbyid(@PathVariable int id) {
		logger.info("access to: / deporte/finbyid/{"+id+"}");
		Optional<Deporte> list = null;
		try {
			list= repository.findById(id);
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/findall")
	public @ResponseBody  Iterable<Deporte> findall() {
		logger.trace("access to: / deporte/findall");
		Iterable<Deporte> list = null;
		try {
			list= repository.findAll();
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/save")
	public Deporte index() {
		logger.trace("access to: / route");
		Deporte deporte = null;
		try {
			Deporte d = new Deporte();
			d.setVersion(1);
			d.setNombre("primer ejemplo");
			repository.save(d);
			deporte= d;
			logger.info("Deporte: guardar");	
		}catch(Exception ex) {
			logger.error(ex);
		}
		
		return deporte;
	}
}
