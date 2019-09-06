package com.deportes.deportes_api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deportes.deportes_api.tablas.Disciplina;

@Repository
@Transactional
public 	interface  ElementTypeRepositorio <T> 
		extends CrudRepository<Disciplina, Integer>, 
				PagingAndSortingRepository<Disciplina, Integer>, 
				JpaSpecificationExecutor<Disciplina>, 
				JpaRepository<Disciplina, Integer>{}