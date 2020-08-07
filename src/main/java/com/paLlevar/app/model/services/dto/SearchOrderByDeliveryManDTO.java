package com.paLlevar.app.model.services.dto;

import java.time.LocalDateTime;

public class SearchOrderByDeliveryManDTO {

	private Integer deliveyId;
	private LocalDateTime initDate;
	private LocalDateTime finalDate;
	private String status;
	
	
	public Integer getDeliveyId() {
		return deliveyId;
	}
	public void setDeliveyId(Integer deliveyId) {
		this.deliveyId = deliveyId;
	}
	public LocalDateTime getInitDate() {
		return initDate;
	}
	public void setInitDate(LocalDateTime initDate) {
		this.initDate = initDate;
	}
	public LocalDateTime getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(LocalDateTime finalDate) {
		this.finalDate = finalDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
