package com.deportes.deportes_api.generic;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.deportes.deportes_api.tools.DateTools;

@SuppressWarnings({"rawtypes","unchecked"})
public class GenericValidations<T> {
	private String moduleName;
	private Boolean isError=false;
	private String errorMessage;
	private DateTools dateTools = new DateTools();
	
	public GenericValidations(String _moduleName){
		this.moduleName=_moduleName;
	}
	
	private String capitalizeString(String param){
		if (param.length()==0) return "";
		return param.substring(0, 1).toUpperCase() + param.substring(1);
	}
	
	
	public void checkIfParamIsNull(T param,String name){
		if (param==null){
			this.setIsError(true);
			this.setErrorMessage(name+" es nulo");
		}else{
			this.setIsError(false);
		}
	}
	
	public void setParametersValuesToElement(Object findedElement, Object _paramsClass){
		String paramName="",methodName;
		Object paramValue;
		GenericClass genericClass;
		try{
			for(Field param: _paramsClass.getClass().getDeclaredFields()){
				
				paramName=param.getName();
				if (!paramName.equals("format")){
					methodName="get"+capitalizeString(paramName);
					genericClass= new GenericClass(_paramsClass,methodName);
					genericClass.executeMethod();
					if (genericClass.getIsError()==true){
						this.setIsError(true); 
						this.setErrorMessage(genericClass.getErrorMessage());
						return;
					}	
					
					paramValue = genericClass.getResult();
					
					if (paramName.equals("createdAt") || paramName.equals("id")){
						System.out.println("param name (ommit): "+paramName+", value "+paramValue);
					}else{
						PropertyUtils.setProperty(findedElement, paramName, paramValue);
						/*genericClass= new GenericClass(findedElement,"set"+capitalizeString(paramName),(T) paramValue);
						genericClass.executeMethod();
						if (genericClass.getIsError()==true){
							this.setIsError(true); 
							this.setErrorMessage(genericClass.getErrorMessage());
							return;
						}*/
					}
				}
				
				
			}
		}catch(Exception exception){
			this.setIsError(true);
			this.setErrorMessage("Error al asignar los valores de parametro "+exception.getMessage());
		}
	}
	

	public void setUpdatedAtInElement(Object _class){
		String setMethodName="setUpdatedAt",getMethodName="getUpdatedAt";
		GenericClass genericClass= new GenericClass(_class,getMethodName);
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		Object beforeDate= genericClass.getResult();

		// set new data in updatedAt attribute
		genericClass = new GenericClass(_class,setMethodName,(T) dateTools.get_CurrentDate());
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		// get updatedAt attribute
		genericClass =new GenericClass(_class,getMethodName);
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		Object result= genericClass.getResult();
		if (result==null){
			this.setIsError(true);
			this.setErrorMessage("Error al asignar la fecha de actualizacion en el modulo "+moduleName);
		}
		if (result==beforeDate){
			this.setIsError(true);
			this.setErrorMessage("La fecha de actualizacion actual y anterior son las mismas en el modulo "+moduleName);
		}
	}
	
	public void setCreatedAtInElement(Object _class){
		String setMethodName="setCreatedAt",getMethodName="getCreatedAt";
		GenericClass genericClass;
		genericClass= new GenericClass(_class,setMethodName,(T) dateTools.get_CurrentDate());
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		genericClass= new GenericClass(_class,getMethodName);
		genericClass.executeMethod();
		if (genericClass.getIsError()==true){
			this.setIsError(true); 
			this.setErrorMessage(genericClass.getErrorMessage());
			return;
		}
		
		if (genericClass.getResult()==null){
			this.setIsError(true);
			this.setErrorMessage("Error al ejecutar "+setMethodName+" en "+moduleName);
		}
	}
	
	public void checkIfListHasElements(List<T> list){
		if (list.size()==0){
			this.setIsError(true);
			this.setErrorMessage(this.moduleName+" no tiene elementos");
		}
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
	 
		
}
