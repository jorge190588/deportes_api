/**
 * 
 */
package com.deportes.deportes_api.tools;

/**
 * @author jorge
 *
 */
public class RestResponse<T> {
	private T data;
	private T error;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public T getError() {
		return error;
	}
	
	public void setError(T error) {
		this.error= error;
	}	
}
