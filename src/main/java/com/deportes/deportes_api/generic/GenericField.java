package com.deportes.deportes_api.generic;
import java.lang.reflect.Field;

public class GenericField<T> {
	private Boolean isError=false;
	private String errorMessage;
	private Object genericClass;
	private Object attribute;
	private Object value;
	
	public GenericField(Object _genericClass){
		this.setGenericClass(_genericClass);
	}
	
	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Object getGenericClass() {
		return genericClass;
	}


	public void setGenericClass(Object genericClass) {
		this.genericClass = genericClass;
	}


	public Object getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		try{
			java.lang.reflect.Field field = this.genericClass.getClass().getDeclaredField(attribute);
		     field.setAccessible(true);
		     Object value = field.get(this.genericClass.getClass());
		     this.attribute = value;	
		}catch(Exception exception){
			this.attribute=null;
			this.setIsError(true);
			this.setErrorMessage("Error to set "+this.attribute+" attribute");
		}
	     
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
