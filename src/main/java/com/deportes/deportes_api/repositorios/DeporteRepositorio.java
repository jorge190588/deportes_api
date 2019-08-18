package com.deportes.deportes_api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.deportes.deportes_api.tablas.Deporte;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DeporteRepositorio<T> extends CrudRepository<Deporte, Integer>, PagingAndSortingRepository<Deporte, Integer>
											, JpaSpecificationExecutor<Deporte>, JpaRepository<Deporte, Integer>{
	List<Deporte> findByNombre(String name);
}
