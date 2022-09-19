package com.api.forum.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.api.forum.model.Topico;

public class TopicoDto {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensage() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public static Page<TopicoDto> topicModelToDto(Page<Topico> topicos){
		return topicos.map(TopicoDto::new);
	}
	

}
