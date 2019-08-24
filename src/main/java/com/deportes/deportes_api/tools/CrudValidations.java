package com.deportes.deportes_api.tools;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import com.deportes.deportes_api.error.CustomException;
import com.deportes.deportes_api.error.ErrorCode;
import com.deportes.deportes_api.error.ErrorFormat;
import com.deportes.deportes_api.generic.GenericClass;
import com.deportes.deportes_api.generic.GenericValidations;
import org.apache.commons.beanutils.PropertyUtils;

@SuppressWarnings({"rawtypes","unchecked"})
public class CrudValidations {
	private Object model;
	private String moduleName;
	private GenericClass genericClass;

	
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
			
			/*List<?> list = (List<?>) ((Optional) genericClass.getResult()).get(); 
			validations.checkIfListHasElements(list);
			if (validations.getIsError()==true) throw new Exception(validations.getErrorMessage());
			Object findedElement = list.get(0);*/
			
			for(Field param: findedElement.getClass().getDeclaredFields()){
			       //you can also use .toGenericString() instead of .getName(). This will
			       //give you the type information as well.

			       System.out.println(param.getName());
			   }
			
			 // Getting the PropertyDescriptors for the object
	        PropertyDescriptor[] objDescriptors = PropertyUtils.getPropertyDescriptors(findedElement);

	        // Iterating through each of the PropertyDescriptors
	        for (PropertyDescriptor objDescriptor : objDescriptors) {
	            try {
	                String propertyName = objDescriptor.getName();
	                Object propType = PropertyUtils.getPropertyType(findedElement, propertyName);
	                Object propValue = PropertyUtils.getProperty(findedElement, propertyName);
	                
	                // Printing the details
	                System.out.println("Property="+propertyName+", Type="+propType+", Value="+propValue);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
			
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
	

 
}
