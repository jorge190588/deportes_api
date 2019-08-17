package com.deportes.deportes_api.cargadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.deportes.deportes_api.repositorios.DeporteRepositorio;

@Component
public class DeporteCargador implements ApplicationListener<ContextRefreshedEvent>  {
	private DeporteRepositorio deporteRepositorio;
	//private Logger log = Logger.getLogger(DeporteCargador.class);
	
    @Autowired
    public void setDeporteRepository(DeporteRepositorio deporteRepositorio) {
        this.deporteRepositorio = deporteRepositorio;
    }
    
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("DeporteCargador");
	}
    
    /*public void onApplicationEvent(ContextRefreshedEvent event) {
    	System.out.println("DeporteCargador");
    	
    	//Deporte deporte = new Deporte();
        //deporte.setNombre("Registro 1");
        //deporteRepositorio.save(deporte);
        //log.info("Saved Shirt - id: " + deporte.getId());
    }*/
}
