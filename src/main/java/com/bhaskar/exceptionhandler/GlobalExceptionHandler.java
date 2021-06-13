package com.bhaskar.exceptionhandler;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bhaskar.dto.ApiErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private ResponseEntity<ApiErrorResponse> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
		
		return new ResponseEntity<ApiErrorResponse>(apiErrorResponse,apiErrorResponse.getStatus());
	}
	
	
	
	@ExceptionHandler
	protected ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,HttpHeaders headers,HttpStatus status,WebRequest request)
	{
		String error ="Malformed Json Request";
		return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST,error,ex));
	}
	


	@ExceptionHandler
	protected ResponseEntity<ApiErrorResponse> handleHttpRequestMethodError(HttpRequestMethodNotSupportedException httpRequestMethod)
	{
		String error ="Method Not Allowed";
		return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, error,httpRequestMethod));
	}
	

}
