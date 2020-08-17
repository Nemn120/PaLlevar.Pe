package com.paLlevar.app.controller.response;

import java.time.LocalDateTime;

public class GenericResponse<T> extends AbstractResponse<T> {
	
	private static final long serialVersionUID = 1L;
	private int index;
	private LocalDateTime  initTimestamp;
	private LocalDateTime  finalTimesTamp;
	
	public GenericResponse() {
		this.initTimestamp = LocalDateTime.now();
	}
	
	public int getIndex() {
		return index;
	}

	public LocalDateTime getInitTimestamp() {
		return initTimestamp;
	}

	public void setInitTimestamp(LocalDateTime initTimestamp) {
		this.initTimestamp = initTimestamp;
	}

	public LocalDateTime getFinalTimesTamp() {
		return finalTimesTamp;
	}

	public void setFinalTimesTamp(LocalDateTime finalTimesTamp) {
		this.finalTimesTamp = finalTimesTamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
