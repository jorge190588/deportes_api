package com.deportes.deportes_api.cargadores;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.deportes.deportes_api.repositorios.DeporteRepositorio;

@SuppressWarnings({"rawtypes","unused"})
@Component
public class DeporteCargador implements ApplicationListener<ContextRefreshedEvent>  {
	
	private DeporteRepositorio deporteRepositorio;
	private Logger log = Logger.getLogger(DeporteCargador.class);
	
    @Autowired
    public void setDeporteRepository(DeporteRepositorio deporteRepositorio) {
        this.deporteRepositorio = deporteRepositorio;
    }
    
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("Inicializar el cargador DeporteCargador");
	}
    
    
}
