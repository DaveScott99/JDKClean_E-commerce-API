package com.jdkclean.jdkcommerce.dto;

public class AuthResponseDTO {

	private String accessToken;
	private String tokenType = "Bearer ";
	
	public AuthResponseDTO() {
	}

	public AuthResponseDTO(String accessToken) {
		this.accessToken = accessToken;
	}

	public AuthResponseDTO(String accessToken, String tokenType) {
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
}
