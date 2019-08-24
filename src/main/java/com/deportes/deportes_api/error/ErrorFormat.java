package com.deportes.deportes_api.error;

public class ErrorFormat {
	ErrorResponse _errorResponse;
	
	public ErrorFormat(CustomException exception){
		_errorResponse=new ErrorResponse();
		_errorResponse.set_className(exception.get_className());
		_errorResponse.set_code(exception.get_code());
		_errorResponse.set_title(exception.get_title());
		_errorResponse.set_message(exception.getMessage());
		_errorResponse.set_stackTrace(exception.get_stackTrace());
		_errorResponse.set_lineNumber(exception.get_lineNumber());
		_errorResponse.set_messageList(exception.get_messageList());
	}
	
	public ErrorResponse get_errorResponse(){
		return this._errorResponse;
	}
}