package com.api.forum.dto;

public class TokenDto {

	private String token;
	private String authType;
	
	public TokenDto(String token, String authType) {
		this.token = token;
		this.authType = authType;
	}

	public String getToken() {
		return token;
	}

	public String getAuthType() {
		return authType;
	}
	
}
