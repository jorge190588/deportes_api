package com.deportes.deportes_api.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deportes.deportes_api.repositorios.DeporteRepositorio;
import com.deportes.deportes_api.tablas.Deporte;
import com.deportes.deportes_api.tools.JPAcustomSpecification;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("deporte")
public class DeporteController<T> {
	@Autowired
	DeporteRepositorio repository;
	Logger logger = Logger.getLogger(DeporteController.class);
	JPAcustomSpecification jpacustomSpecification = new JPAcustomSpecification();
	
	
	@PostMapping("/save")
	public Deporte save(@RequestBody Deporte newDeporte) {
		logger.info("access to: post /save route");
		Deporte deporte = null;
		try {
			deporte= (Deporte) repository.save(newDeporte);
			logger.info("Deporte: registro guardado");	
		}catch(Exception ex) {
			logger.error(ex);
		}
		return deporte;
	}
	
	@DeleteMapping("/{id}")
	public Boolean delete(@PathVariable Integer id) {
		logger.info("access to: Delete /{"+id+"} route");
		try {
			repository.deleteById(id);;
			return true;
		}catch(Exception ex) {
			logger.error(ex);
			return false;
		}
	}
	
	@PutMapping("/{id}")
	public Deporte updateEmployee(@RequestBody Deporte newDeporte, @PathVariable Integer id) {
		try {
			return (Deporte) repository.findById(id)
				      .map(deporte -> {
				        ((Deporte) deporte).setNombre(newDeporte.getNombre());
				        ((Deporte) deporte).setVersion(newDeporte.getVersion());
				        return repository.save(deporte);
				      })
				      .orElseGet(() -> {
				    	  newDeporte.setId(id);
				        return repository.save(newDeporte);
				      });	
		}catch(Exception ex) {
			logger.error(ex);
			return null;
		}
		
	}
	
	@GetMapping("/{id}")
	public @ResponseBody  Optional<Deporte> finbyid(@PathVariable int id) {
		logger.info("access to: / deporte/"+id);
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
	public  @ResponseBody  Page  page(	@PathVariable int pageNumber,@PathVariable int pageSize,
			@RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
		logger.info("access to: / deporte/page/"+pageNumber+"/"+pageSize+"?searchCriteria="+searchCriteria+"&orderCriteria="+orderCriteria);
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null; 
		try {
			 logger.info("filter "+searchCriteria);
			 if (!searchCriteria.isEmpty())	searchCriteriaArray = new JSONArray(searchCriteria.get());
			 if (!orderCriteria.isEmpty())	orderCriteriaArray = new JSONArray(orderCriteria.get());
			 Page<?> page = repository.findAll(jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ),PageRequest.of(pageNumber, pageSize));
			 page.getTotalElements();
		     page.getTotalPages();   
		     return page;
		}catch(Exception ex){
			logger.error(ex);
			return null;
		}
		
	}
	    
}
