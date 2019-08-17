package com.deportes.deportes_api.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deportes.deportes_api.repositorios.DeporteRepositorio;
import com.deportes.deportes_api.tablas.Deporte;
import com.deportes.deportes_api.tools.JPAcustomSpecification;

@RestController
@RequestMapping("deporte")
public class DeporteController<T> {
	@Autowired
	DeporteRepositorio repository;
	Logger logger = Logger.getLogger(DeporteController.class);
	JPAcustomSpecification jpacustomSpecification = new JPAcustomSpecification();
	
	@GetMapping("/save")
	public Deporte index() {
		logger.info("access to: / route");
		Deporte deporte = null;
		try {
			Deporte d = new Deporte();
			d.setVersion(1);
			d.setNombre("primer ejemplo");
			repository.save(d);
			deporte= d;
			logger.info("Deporte: registro guardado");	
		}catch(Exception ex) {
			logger.error(ex);
		}
		
		return deporte;
	}
	
	@GetMapping("/finbyid/{id}")
	public @ResponseBody  Optional<Deporte> finbyid(@PathVariable int id) {
		logger.info("access to: / deporte/finbyid/"+id);
		Optional<Deporte> list = null;
		try {
			list= repository.findById(id);
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/findbynombre/{name}")
	public @ResponseBody  List<Deporte> finbyname(@PathVariable String name) {
		logger.info("access to: / deporte/finbynombre/"+name);
		List<Deporte> list = null;
		try {
			list= repository.findByNombre(name);
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/findbynombreyversion/{name}/{version}")
	public @ResponseBody  List<Deporte> finbynameyversion(@PathVariable String name,@PathVariable int version) {
		logger.info("access to: / deporte/finbynombre/"+name+"/"+version);
		List<Deporte> list = null;
		try {
			list= repository.findByNombreAndVersion(name,version);
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/findall")
	public @ResponseBody  Iterable<Deporte> findall() {
		logger.info("access to: / deporte/findall");
		Iterable<Deporte> list = null;
		try {
			list= repository.findAll();
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/page/{pageNumber}/{pageSize}")
	public  @ResponseBody  Page  page(@PathVariable int pageNumber,@PathVariable int pageSize,@RequestParam String searchCriteria,@RequestParam String orderCriteria) {
		logger.info("access to: / deporte/page/"+pageNumber+"/"+pageSize+"?searchCriteria="+searchCriteria+"&orderCriteria="+orderCriteria);
		Page<?> page=null;
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null; 
		try {
			 logger.info("filter "+searchCriteria);
			 if (searchCriteria.length()>0)	searchCriteriaArray = new JSONArray(searchCriteria);
			 if (orderCriteria.length()>0)	orderCriteriaArray = new JSONArray(orderCriteria);
			 page = repository.findAll(jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ),PageRequest.of(pageNumber, pageSize));
			 page.getTotalElements();
		     page.getTotalPages();   

		}catch(Exception ex){
			logger.error(ex);
		}
		return page;
	}
	    
}
