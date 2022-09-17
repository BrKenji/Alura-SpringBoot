package com.api.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.forum.model.Curso;

public class CursoForm {

	@NotEmpty @NotNull
	private String nome;
	
	@NotEmpty @NotNull
	private String categoria;

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
	
	public Curso cursoFormToModel() {
		return new Curso(nome, categoria);
	}
	
}
