package com.api.forum.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.forum.model.Curso;
import com.api.forum.repository.CursoRepository;

public class UpdateCursoForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String categoria;
	
	public Curso updateCurso(Long id, CursoRepository cursoRepository) {
		Curso curso = cursoRepository.getReferenceById(id);
		curso.setCategoria(categoria);
		curso.setNome(nome);
		
		return curso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
}
