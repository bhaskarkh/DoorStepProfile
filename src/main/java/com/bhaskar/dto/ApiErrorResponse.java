package com.bhaskar.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class ApiErrorResponse {
	
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMesage;
	private List<ApiSubError> subErrors;
	
	private ApiErrorResponse()
	{
		this.timestamp=LocalDateTime.now();
		
	}
	
	public ApiErrorResponse(HttpStatus status)
	{
		this();
		this.status=status;
	}
	
	public ApiErrorResponse(HttpStatus status,Throwable ex)
	{
		
		this();
		this.status=status;
		this.message="Unexpected Error";
		this.debugMesage=ex.getLocalizedMessage();
	}
	
	public ApiErrorResponse(HttpStatus status,String message,Throwable ex)
	{
		
		this();
		this.status=status;
		this.message=message;
		this.debugMesage=ex.getLocalizedMessage();
	}
	
	

}
