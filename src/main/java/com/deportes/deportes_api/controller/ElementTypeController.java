package com.deportes.deportes_api.controller;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deportes.deportes_api.repositorios.ElementRepositorio;
import com.deportes.deportes_api.repositorios.ElementTypeRepositorio;
import com.deportes.deportes_api.tablas.ElementType;
import com.deportes.deportes_api.tools.CrudValidations;
import com.deportes.deportes_api.tools.DateTools;
import com.deportes.deportes_api.tools.JPAcustomSpecification;
import com.deportes.deportes_api.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
@RestController
@RequestMapping("elementType")
public class ElementTypeController <T> {
	@Autowired
	ElementTypeRepositorio repository;
	Logger logger = Logger.getLogger(this.getClass());
	JPAcustomSpecification jpacustomSpecification = new JPAcustomSpecification();
	DateTools dateTools = new DateTools();
	CrudValidations crud = null;
	private String moduleName="ElementType";
	
	@Autowired
	ElementRepositorio elementRepository;
	
	private void instanceCrud() {
		if (crud==null) crud = new CrudValidations(repository,moduleName,elementRepository );
	}
	
	@PostMapping("")
	public RestResponse create(@RequestBody ElementType newElement) {
		logger.info("access to: POST /"+moduleName+"/"+newElement);
		instanceCrud();
		return crud.create(newElement);
	}
	
	@DeleteMapping("/{id}")
	public RestResponse delete(@PathVariable Integer id) {
		logger.info("access to: DELETE /"+moduleName+"/"+id);
		instanceCrud();
		return crud.delete(id.toString());
	}
	
	@PutMapping("/{id}")
	public RestResponse update(@RequestBody ElementType updateElement) {
		logger.info("access to: PUT /"+moduleName+"/"+updateElement.getId());
		instanceCrud();
		return crud.update(updateElement);
	}
	
	@GetMapping("/{id}")
	public RestResponse  finbyid(@PathVariable int id) {
		logger.info("access to: GET /"+moduleName+"/"+id);
		instanceCrud(); 
		return crud.findById(id);
	}
	
	@GetMapping("")
	public RestResponse findall(@RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
		logger.info("access to: GET /"+moduleName+"/findall?searchCriteria="+searchCriteria+"&orderCriteria="+orderCriteria);
		instanceCrud(); 
		return crud.findAll(searchCriteria, orderCriteria);
	}
	
	@GetMapping("/{page}/{rows}")
	public  RestResponse  page(	@PathVariable int page,@PathVariable int rows,
			@RequestParam("searchCriteria") Optional<String> searchCriteria,@RequestParam Optional<String> orderCriteria) {
		logger.info("access to: GET /"+moduleName+"/page/"+page+"/"+rows+"?searchCriteria="+searchCriteria+"&orderCriteria="+orderCriteria);
		instanceCrud(); 
		return crud.getPage(searchCriteria, orderCriteria, page, rows);
	}
}
