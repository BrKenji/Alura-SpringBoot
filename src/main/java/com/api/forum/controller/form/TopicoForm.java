package com.api.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import com.api.forum.model.Curso;
import com.api.forum.model.Topico;
import com.api.forum.repository.CursoRepository;

public class TopicoForm {

	@NotEmpty @NotNull @Length(min = 5)
	private String titulo;
	
	@NotEmpty @NotNull @Length(min = 10)
	private String mensagem;
	
	@NotEmpty @NotNull
	private String nomeCurso;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico topicFormToModel(CursoRepository cursoRepo) {
		Curso curso = cursoRepo.findByNome(nomeCurso);
		System.out.println(cursoRepo.findByNome(nomeCurso).getNome());
		return new Topico(titulo, mensagem, curso);
	}
	
}
