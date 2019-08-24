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
	
	public T get_data() {
		return data;
	}

	public void set_data(T data) {
		this.data = data;
	}
	
	public T get_error() {
		return error;
	}
	
	public void set_error(T error) {
		this.error= error;
	}	
}
