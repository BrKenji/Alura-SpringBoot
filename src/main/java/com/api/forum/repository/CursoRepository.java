package com.api.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String cursoNome);

}
