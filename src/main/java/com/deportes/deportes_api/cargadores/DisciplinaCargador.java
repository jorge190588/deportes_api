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
	private DisciplinaRepositorio disciplinaRepositorio;
	private Logger log = Logger.getLogger(DisciplinaCargador.class);
	
    @Autowired
    public void setDeporteRepository(DisciplinaRepositorio disciplinaRepositorio) {
        this.disciplinaRepositorio = disciplinaRepositorio;
    }
    
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("Inicializar el cargador DisciplinaCargador");
	}
     
}
