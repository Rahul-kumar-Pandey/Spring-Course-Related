package com.Cart.ShoppingApp.Exception;

public class ResourceNotFoundException  extends RuntimeException{
	String resourseName;
	String field;
	String fieldName;
	Long fieldId;
	public ResourceNotFoundException() {
		
	}
	public ResourceNotFoundException(String resourseName, String field, String fieldName) {
		super(String.format("%s is not found with:%s, %s",resourseName,field,fieldName));
		this.resourseName = resourseName;
		this.field = field;
		this.fieldName = fieldName;
	}
	public ResourceNotFoundException(String resourseName, String fieldName, Long fieldId) {
		super(String.format("%s is not found with:%s, %s",resourseName,fieldName,fieldId));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
	
}
