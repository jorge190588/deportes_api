package com.deportes.deportes_api.controller;

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
import com.deportes.deportes_api.tools.CrudValidations;
import com.deportes.deportes_api.tools.DateTools;
import com.deportes.deportes_api.tools.JPAcustomSpecification;
import com.deportes.deportes_api.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("deporte")
public class DeporteController<T> {
	@Autowired
	DeporteRepositorio repository;
	Logger logger = Logger.getLogger(DeporteController.class);
	JPAcustomSpecification jpacustomSpecification = new JPAcustomSpecification();
	DateTools dateTools = new DateTools();
	CrudValidations crud = new CrudValidations(repository,"Deporte");
	
	private void instanceCrud() {
		if (crud!=null) crud = new CrudValidations(repository,"Deporte");
	}
	
	@PostMapping("/save")
	public RestResponse save(@RequestBody Deporte newDeporte) {
		logger.info("access to: post /save route");
		instanceCrud();
		return crud.create(newDeporte);
	}
	
	@DeleteMapping("/{id}")
	public RestResponse delete(@PathVariable Integer id) {
		logger.info("access to: Delete /{"+id+"} route");
		instanceCrud();
		return crud.delete(id.toString());
	}
	
	@PutMapping("/{id}")
	public RestResponse update(@RequestBody Deporte updateElement, @PathVariable Integer id) {
		instanceCrud();
		return crud.update(updateElement);
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
	
	
	@GetMapping("/findall")
	public @ResponseBody  Iterable<Deporte> findall(@RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
		logger.info("access to: / deporte/findall?searchCriteria="+searchCriteria+"&orderCriteria="+orderCriteria);
		Iterable<Deporte> list = null;
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null;
		try {
			 if (!searchCriteria.isEmpty())	searchCriteriaArray = new JSONArray(searchCriteria.get());
			 if (!orderCriteria.isEmpty())	orderCriteriaArray = new JSONArray(orderCriteria.get());
			 list= repository.findAll(jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ));
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
