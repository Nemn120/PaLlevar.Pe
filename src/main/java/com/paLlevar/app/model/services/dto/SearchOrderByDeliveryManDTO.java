package com.paLlevar.app.model.services.dto;

import java.time.LocalDateTime;

public class SearchOrderByDeliveryManDTO {

	private Integer deliveryId;
	private LocalDateTime initDate;
	private LocalDateTime finalDate;
	private String status;
	
	
	public Integer getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Integer deliveyId) {
		this.deliveryId = deliveyId;
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
