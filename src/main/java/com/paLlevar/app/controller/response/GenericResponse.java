package com.paLlevar.app.controller.response;

public class GenericResponse<T> extends AbstractResponse<T> {
	
	private static final long serialVersionUID = 1L;
	private int index;
	
	public int getIndex() {
		return index;
	}
}
