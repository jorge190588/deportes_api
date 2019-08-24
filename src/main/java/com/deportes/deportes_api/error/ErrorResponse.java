/**
 * 
 */
package com.deportes.deportes_api.error;

import java.util.List;

/**
 * @author jorge
 *
 */
public class ErrorResponse {
	private String _className;
	private int _code;
	private String _title;
	private String _message;
	private int _lineNumber;
	private List<ErrorMessage> _messageList;
	private String _stackTrace;

	public ErrorResponse(){}
	
	public String get_message() {
		return _message;
	}
	public void set_message(String _message) {
		this._message = _message;
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
