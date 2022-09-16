package com.api.forum.dto;

public class FormErrorDto {
	
	private String campo;
	private String mensageErro;
	
	public FormErrorDto(String campo, String mensageErro) {
		this.campo = campo;
		this.mensageErro = mensageErro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensageErro() {
		return mensageErro;
	}
	
}
