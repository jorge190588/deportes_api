package com.deportes.deportes_api.error;

public class ErrorMessage {
	private String _message;
	private int _line;
	private String _methodName;
	private String _fileName; 
	private String _className;
	
	//public ErrorMessage(){}

	public String get_message() {
		return _message;
	}

	public void set_message(String _message) {
		this._message = _message;
	}

	public int get_line() {
		return _line;
	}

	public void set_line(int _line) {
		this._line = _line;
	}

	public String get_methodName() {
		return _methodName;
	}

	public void set_methodName(String _methodName) {
		this._methodName = _methodName;
	}

	public String get_fileName() {
		return _fileName;
	}

	public void set_fileName(String _fileName) {
		this._fileName = _fileName;
	}

	public String get_className() {
		return _className;
	}

	public void set_className(String _className) {
		this._className = _className;
	}
	
	
}
