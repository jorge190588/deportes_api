package com.deportes.deportes_api.repositorios;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.deportes.deportes_api.tablas.Deporte;

@Repository
public interface DeporteRepositorio extends CrudRepository<Deporte, Integer>, PagingAndSortingRepository<Deporte, Integer>{
	  List<Deporte> findByNombre(String nombre);
	  List<Deporte> findByNombreAndVersion(String nombre, int version);
}
