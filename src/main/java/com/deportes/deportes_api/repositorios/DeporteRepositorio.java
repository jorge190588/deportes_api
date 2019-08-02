package com.deportes.deportes_api.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deportes.deportes_api.tablas.Deporte;
@Repository
public interface  DeporteRepositorio extends CrudRepository<Deporte, Integer>{}
