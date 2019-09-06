package com.deportes.deportes_api.cargadores;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.deportes.deportes_api.repositorios.DisciplinaRepositorio;

@SuppressWarnings({"rawtypes","unused"})
@Component
public class DisciplinaCargador implements ApplicationListener<ContextRefreshedEvent>  {
	private DisciplinaRepositorio repository;
	private Logger log = Logger.getLogger(this.getClass());
	
    @Autowired
    public void setRepository(DisciplinaRepositorio repository) {
        this.repository = repository;
    }
    
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("Inicializar el cargador "+this.getClass().getSimpleName().toString());
	}
}