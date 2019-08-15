package com.deportes.deportes_api.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
		logger.trace("access to: / deporte/findall");
		Iterable<Deporte> list = null;
		try {
			list= repository.findAll();
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
	
	@GetMapping("/page/{sortValues}/{sortType}/{pageNumber}/{pageSize}")
	public  @ResponseBody  Page<Deporte>  page(@PathVariable String sortValues,@PathVariable String sortType,@PathVariable int pageNumber,@PathVariable int pageSize) {
		logger.trace("access to: / deporte/page/"+sortValues+"/"+sortType+"/"+pageNumber+"/"+pageSize);
		Page<Deporte>  list = null;
		PageRequest pageable = null;
		try {
			if (sortType.equals("desc")) pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortValues).descending());
			else pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortValues).ascending());
			list = repository.findAll(pageable);
		}catch(Exception ex){
			logger.error(ex);
		}
		return list;
	}
}
