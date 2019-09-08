package com.deportes.deportes_api.tools;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.deportes.deportes_api.error.CustomException;
import com.deportes.deportes_api.error.ErrorCode;
import com.deportes.deportes_api.error.ErrorFormat;
import com.deportes.deportes_api.generic.GenericClass;
import com.deportes.deportes_api.generic.GenericValidations;

@SuppressWarnings({"rawtypes","unchecked"})
public class CrudValidations<T>   {
	private Object model;
	private String moduleName;
	private GenericClass genericClass;
	private JPAcustomSpecification jpacustomSpecification = new JPAcustomSpecification();
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private javax.validation.Validator validator =  factory.getValidator();
    private Object elementRepository;
    private GenericValidations validations;
    
	public CrudValidations(Object _model,String _moduleName, Object _elementRepository){
		this.model=_model;
		this.moduleName=_moduleName;
		this.elementRepository=_elementRepository;
		this.validations = new GenericValidations(moduleName, _elementRepository);
	}
	
	public RestResponse update(Object updateElement){
		RestResponse response = new RestResponse();
		
		GenericClass genericClass;
		try {
			validations.checkIfParamIsNull(updateElement,moduleName);
			
			Set<ConstraintViolation<Object>> errors = validator.validate(updateElement);
		    if (errors != null && errors.size() != 0) {
		    	CustomException ex = new CustomException("Error en las validaciones", ErrorCode.REST_CREATE, this.getClass().getSimpleName(), 0, errors);
		    	ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.setError(_errorFormat.get_errorResponse());
				return response;
		    }
			    
			validations.setUpdatedAtInElement(updateElement);
			
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
			
			genericClass = new GenericClass(model,"save",findedElement);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());	

			response.setData(genericClass.getResult());
						
		}catch(Throwable exception ){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_UPDATE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.setError(_errorFormat.get_errorResponse());
		}
		return response;
	}
	
	public RestResponse create(Object newElement, Object repository) {
		
		RestResponse response= new RestResponse() ;
		try{
			
			validations.checkIfParamIsNull(newElement,moduleName);
		
		    /*Set<ConstraintViolation<Object>> errors = validator.validate(newElement);
		    if (errors != null && errors.size() != 0) {
		    	CustomException ex = new CustomException("Error en las validaciones", ErrorCode.REST_CREATE, this.getClass().getSimpleName(), 0, errors);
		    	ErrorFormat _errorFormat = new ErrorFormat(ex);
				response.setError(_errorFormat.get_errorResponse());
				return response;
		    }*/
			
			validations.validations(newElement, repository);
			
			validations.setCreatedAtInElement(newElement);
				
			genericClass = new GenericClass(model,"save",newElement);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.setData(genericClass.getResult());
		}catch(CustomException exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_CREATE,this.getClass().getSimpleName(),exception.getMessageList());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response = new RestResponse();
			response.setError(_errorFormat.get_errorResponse());
		}catch(Throwable exception ) {
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_CREATE,this.getClass().getSimpleName());
			ErrorFormat errorFormat = new ErrorFormat(ex);
			response = new RestResponse();
			response.setError(errorFormat.get_errorResponse());
		}
		return response;
	}
 
	public RestResponse delete(String id){
		RestResponse response = new RestResponse();
		 
		GenericClass genericClass;
		try{
			 
			genericClass= new GenericClass(model,"getAll","id="+id);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());
			List<?> listWithSpecificId = (List<?>) genericClass.getResult(); 
			
			validations.checkIfParamIsNull(listWithSpecificId,moduleName);
			validations.checkIfListHasElements(listWithSpecificId);
			
			genericClass = new GenericClass(model,"delete",listWithSpecificId.get(0));
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.setData(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_DELETE,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.setError(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	public RestResponse findById(Integer id) {
		RestResponse response = new RestResponse();
		 
		GenericClass genericClass;
		try{
		 
		
			validations.checkIfParamIsNull(id,moduleName);
			
			genericClass = new GenericClass(model,"findById",id);
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.setData(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.setError(_errorFormat.get_errorResponse());
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
			response.setData(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.setError(_errorFormat.get_errorResponse());
		} 
		return response;
	}
	
	public RestResponse findAllE(java.util.Optional searchCriteria, java.util.Optional orderCriteria) {
		RestResponse response = new RestResponse();
		GenericClass genericClass;
		JSONArray searchCriteriaArray=null, orderCriteriaArray=null;
		try{
			if (!searchCriteria.isEmpty())	searchCriteriaArray = new JSONArray(searchCriteria.get().toString());
			if (!orderCriteria.isEmpty())	orderCriteriaArray = new JSONArray(orderCriteria.get().toString());
			genericClass = new GenericClass(model,"findAll",jpacustomSpecification.getSpecification(searchCriteriaArray,orderCriteriaArray ));
			genericClass.executeMethod();
			if (genericClass.getIsError()==true) throw new Exception(genericClass.getErrorMessage());			
			response.setData(genericClass.getResult());
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.setError(_errorFormat.get_errorResponse());
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
			response.setData(page);
		}catch(Throwable exception){
			CustomException ex=  new CustomException(exception.getMessage(),exception,ErrorCode.REST_FIND,this.getClass().getSimpleName());
			ErrorFormat _errorFormat = new ErrorFormat(ex);
			response.setError(_errorFormat.get_errorResponse());
		} 
		return response;
	}
}
