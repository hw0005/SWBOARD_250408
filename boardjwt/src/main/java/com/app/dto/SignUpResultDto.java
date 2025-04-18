package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResultDto {
	// DTO Data Transfer Object => Java Bean
	private boolean success;
	private int code;
	private String msg;
	
	
}
