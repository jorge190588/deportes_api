package com.deportes.deportes_api.tools;

import java.util.List;
import java.util.Optional;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.deportes.deportes_api.error.CustomException;
import com.deportes.deportes_api.error.ErrorCode;
import com.deportes.deportes_api.error.ErrorFormat;
import com.deportes.deportes_api.generic.GenericClass;
import com.deportes.deportes_api.generic.GenericValidations;

@SuppressWarnings({"rawtypes","unchecked"})
public class CrudValidations {
	private Object model;
	private String moduleName;
	private GenericClass genericClass;
	private JPAcustomSpecification jpacustomSpecification = new JPAcustomSpecification();
	
	public CrudValidations(Object _model,String _moduleName){
		this.model=_model;
		this.moduleName=_moduleName;
	}
	
	public RestResponse update(Object updateElement){
		RestResponse response = new RestResponse();
		GenericValidations validations; 
		GenericClass genericClass;
		try {
			validations = new GenericValidations(moduleName);
			validations.checkIfParamIsNull(updateElement,moduleName);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			// set updatedAt attribute
			validations.setUpdatedAtInElement(updateElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());

			
			// check list
			genericClass= new GenericClass(updateElement,"getId");
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			Object id = genericClass.getResult();
			
			genericClass= new GenericClass(model,"findById",id);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			
			if (genericClass.getResult().equals(Optional.empty())) throw new Exception("Id no existe");
			
			Object findedElement = ((Optional) genericClass.getResult()).get();
			
			// set parameters
			validations.setParametersValuesToElement(findedElement,updateElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			genericClass = new GenericClass(model,"save",findedElement);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());	

			response.set_data(genericClass.getResult());
						
		}catch(Exception exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		}
		return response;
	}
	
	public RestResponse create(Object newElement){
		GenericValidations validations;
		RestResponse response= new RestResponse();
		try{
			validations = new GenericValidations(moduleName);
			validations.checkIfParamIsNull(newElement,moduleName);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			validations.setCreatedAtInElement(newElement);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
				
			genericClass = new GenericClass(model,"save",newElement);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.set_data(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_CREATE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response = new RestResponse();
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
 
	public RestResponse delete(String id){
		RestResponse response = new RestResponse();
		GenericValidations validations;
		GenericClass genericClass;
		try{
			validations = new GenericValidations(moduleName);
			
			genericClass= new GenericClass(model,"getAll","id="+id);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			List<?> listWithSpecificId = (List<?>) genericClass.getResult(); 
			
			validations.checkIfParamIsNull(listWithSpecificId,moduleName);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			validations.checkIfListHasElements(listWithSpecificId);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			
			genericClass = new GenericClass(model,"delete",listWithSpecificId.get(0));
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.set_data(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_DELETE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	public RestResponse findById(Integer id) {
		RestResponse response = new RestResponse();
		GenericValidations validations;
		GenericClass genericClass;
		try{
			validations = new GenericValidations(moduleName);
		
			validations.checkIfParamIsNull(id,moduleName);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
		 
			genericClass = new GenericClass(model,"findById",id);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.set_data(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	public RestResponse findAll(Optional<String> searchCriteria, Optional<String> orderCriteria) {
		RestResponse response = new RestResponse();
		GenericClass genericClass;
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null;
		try{
			if (!searchCriteria.isEmpty())	searchCriteriaArray = new JSONArray(searchCriteria.get());
			if (!orderCriteria.isEmpty())	orderCriteriaArray = new JSONArray(orderCriteria.get());
			genericClass = new GenericClass(model,"findAll",jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ));
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.set_data(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	public RestResponse getPage(Optional<String> searchCriteria, Optional<String> orderCriteria, int pageNumber, int pageSize) {
		RestResponse response = new RestResponse();
		GenericClass genericClass;
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null;
		try{
			if (!searchCriteria.isEmpty())	searchCriteriaArray = new JSONArray(searchCriteria.get());
			if (!orderCriteria.isEmpty())	orderCriteriaArray = new JSONArray(orderCriteria.get());
			genericClass = new GenericClass(model,"findAll",new Object []{jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ),PageRequest.of(pageNumber, pageSize)});
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			Page<?> page = (Page<?>) genericClass.getResult();
			page.getTotalElements();
		    page.getTotalPages();   
			response.set_data(page);
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.set_error(_errorFormat.get_errorResponse());
		} 
		return response;
	}
}
