package com.api.forum.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.api.forum.model.Topico;
import com.api.forum.repository.TopicoRepository;

// UpdateForm is used so only some of the attributes from the TopicoForm can be changed
public class UpdateTopicoForm {

	@NotEmpty @NotNull @Length(min = 5)
	private String titulo;
	
	@NotEmpty @NotNull @Length(min = 10)
	private String mensagem;

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

	public Topico update(Long id, TopicoRepository topicoRepository) {
		
		Topico topico = topicoRepository.getReferenceById(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		
		return topico;
		
	}
	
	
	
}
