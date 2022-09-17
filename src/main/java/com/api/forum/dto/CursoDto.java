package com.api.forum.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.api.forum.model.Curso;
import com.api.forum.model.Topico;

public class CursoDto {

	private Long id;
	private String nome;
	private String categoria;
	
	public CursoDto(Curso curso) {
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.categoria = curso.getCategoria();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public static List<CursoDto> cursoModelToDto(List<Curso> cursos){
		return cursos.stream().map(CursoDto::new).collect(Collectors.toList());
	}
	
}
