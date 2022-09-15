package com.api.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public static List<TopicoDto> topicModelToDto(List<Topico> topicos){
		return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
	}
	

}
