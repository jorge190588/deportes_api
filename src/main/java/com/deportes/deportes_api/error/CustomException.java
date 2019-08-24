/**
 * 
 */
package com.deportes.deportes_api.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jorge
 *
 */
public class CustomException extends Throwable  {
	private int _code;
	private String _title;
	private String _className;
	private int _lineNumber;
	private List<ErrorMessage> _messageList;
	private String _stackTrace;
	
	public CustomException(String message, Throwable cause, ErrorCode errorCode,String className) {
		super(message, cause);
		this.set_className(className);
		this.set_code(errorCode.get_code());
		this.set_title(errorCode.get_title());
		this.set_stackTrace(Arrays.toString(cause.getStackTrace()));
		this.set_lineNumber(cause.getStackTrace()[0].getLineNumber());
		this.set_messageList(getMessageList(cause));
	}
	
	public CustomException(CustomException exception) {
		super(exception.getMessage(), exception.getCause());
		this.set_className(exception.get_className());
		this.set_code(exception.get_code());
		this.set_title(exception.get_title());
		this.set_stackTrace(Arrays.toString(exception.getStackTrace()));
		this.set_lineNumber(exception.getStackTrace()[0].getLineNumber());
		this.set_messageList(getMessageList(exception));
	}

	public CustomException(String message,  ErrorCode errorCode,String className, int lineNumber) {
		super(message);
		this.set_className(className);
		this.set_code(errorCode.get_code());
		this.set_title(errorCode.get_title());
		this.set_stackTrace(null);
		this.set_lineNumber(lineNumber);
		this.set_messageList(null);
	}
	
	

	public List<ErrorMessage> getMessageList(Throwable throwable) {
		List<ErrorMessage> errorMessageList =  new ArrayList<ErrorMessage>();
	    while (throwable != null) {
	    	ErrorMessage message = new ErrorMessage();
	    	message.set_message( throwable.getMessage());
	    	message.set_line(throwable.getStackTrace()[0].getLineNumber());
	    	message.set_methodName(throwable.getStackTrace()[0].getMethodName());
	    	message.set_fileName(throwable.getStackTrace()[0].getFileName() );
	    	message.set_className(throwable.getStackTrace()[0].getClassName());
	    	errorMessageList.add(message);
	    	throwable = throwable.getCause();
	    }
	    return errorMessageList; 
	}
	
	public int get_code() {
		return _code;
	}

	public void set_code(int _code) {
		this._code = _code;
	}

	public String get_className() {
		return _className;
	}

	public void set_className(String _className) {
		this._className = _className;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_stackTrace() {
		return _stackTrace;
	}

	public void set_stackTrace(String _stackTrace) {
		this._stackTrace = _stackTrace;
	}

	public int get_lineNumber() {
		return _lineNumber;
	}

	public void set_lineNumber(int _lineNumber) {
		this._lineNumber = _lineNumber;
	}

	public List<ErrorMessage> get_messageList() {
		return _messageList;
	}

	public void set_messageList(List<ErrorMessage> _messageList) {
		this._messageList = _messageList;
	}

	
}
