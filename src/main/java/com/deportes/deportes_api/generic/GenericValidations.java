package com.deportes.deportes_api.generic;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deportes.deportes_api.repositorios.ElementRepositorio;
import com.deportes.deportes_api.tablas.Entiti;
import com.deportes.deportes_api.tablas.Element;
import com.deportes.deportes_api.tools.CrudValidations;
import com.deportes.deportes_api.tools.DateTools;
import com.deportes.deportes_api.tools.RestResponse;

@SuppressWarnings({"rawtypes","unchecked"})
public class GenericValidations<T> {
	private String moduleName;
	private Boolean isError=false;
	private String errorMessage;
	private DateTools dateTools = new DateTools();
	private Object elementRepository;
	private CrudValidations elementCrud = null;
	private GenericClass genericClass;
	
	public GenericValidations(String _moduleName, Object _elementRepository){
		this.moduleName=_moduleName;
		this.elementRepository=_elementRepository;
	}
	
	private void instanceCrud() {
		if (elementCrud==null) elementCrud = new CrudValidations(elementRepository,moduleName,elementRepository);
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
	
	public void validations(Object _class) {
		instanceCrud();
		Entiti entiti = null;
		Optional<String> searchFilter =  Optional.of("[{\"id\":\"entiti.name\",\"option\":\"Igual\",\"value\":\"deporte\"}]");
		Optional<String> orderFilter =  Optional.empty();
		
		RestResponse response  = elementCrud.findAll(searchFilter, orderFilter);
		List<Element> listOfElements = (List<Element>) response.getData();
		String idElement="", methodName="",message="",pattern="";
		int errorCounter=0;
		Boolean matches=false;
		
		for(Element element: listOfElements){
			
			idElement =capitalizeString(element.getIdelement());
			methodName="get"+idElement;
			genericClass = new GenericClass(_class,methodName);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true){
				this.setIsError(true); 
				this.setErrorMessage(genericClass.getErrorMessage());
				return;
			}	
			
			if (genericClass.getResult()==null){
				this.setIsError(true);
				if(errorCounter>0) message+=", "; 
				message+=idElement;
				errorCounter++;
			}else{
				pattern= element.getPattern();
				if (genericClass.getResult() instanceof BigDecimal)
					matches= new BigDecimal(((BigDecimal) genericClass.getResult()).longValueExact()).toString().matches(pattern);
				else
					matches= genericClass.getResult().toString().matches(pattern);
				if (matches==false){
					this.setIsError(true);
					if(errorCounter>0) message+=", ";
					message+=idElement+"("+element.getPattern()+") con el formato "+element.getPatternMessage(); 
					errorCounter++;	
				}
			}
		}
		
		if (errorCounter==1) this.setErrorMessage("el parametro "+message+" es requerido");
		else if (errorCounter>1) this.setErrorMessage("los parametros "+message+" son requeridos");
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
