package com.Cart.ShoppingApp.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Cart.ShoppingApp.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String,String>response=new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(err->{
			String fieldName=err.getObjectName();
			String message=err.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>myResourceNotFoundException(ResourceNotFoundException e){
		String message=e.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse>myApiException(ApiException e){
		String message=e.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
	}
}
