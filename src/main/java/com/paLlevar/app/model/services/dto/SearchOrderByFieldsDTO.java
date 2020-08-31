package com.paLlevar.app.model.services.dto;

import java.util.Date;


public class SearchOrderByFieldsDTO {

	private String status;
	private String userOrderDocumentNumber;//user
	private Date deliveryDateIni;
	private Date deliveryDateFin;
	private Date attendDateIni;
	private Date attendDateFin;
	private Integer userDeliveryId;
	private Integer userAttendId; //orderDetail
	private Integer organizationId;
	
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserOrderDocumentNumber() {
		return userOrderDocumentNumber;
	}
	public void setUserOrderDocumentNumber(String userOrderDocumentNumber) {
		this.userOrderDocumentNumber = userOrderDocumentNumber;
	}
	public Date getDeliveryDateIni() {
		return deliveryDateIni;
	}
	public void setDeliveryDateIni(Date deliveryDateIni) {
		this.deliveryDateIni = deliveryDateIni;
	}
	public Date getDeliveryDateFin() {
		return deliveryDateFin;
	}
	public void setDeliveryDateFin(Date deliveryDateFin) {
		this.deliveryDateFin = deliveryDateFin;
	}
	public Date getAttendDateIni() {
		return attendDateIni;
	}
	public void setAttendDateIni(Date attendDateIni) {
		this.attendDateIni = attendDateIni;
	}
	public Date getAttendDateFin() {
		return attendDateFin;
	}
	public void setAttendDateFin(Date attendDateFin) {
		this.attendDateFin = attendDateFin;
	}
	public Integer getUserDeliveryId() {
		return userDeliveryId;
	}
	public void setUserDeliveryId(Integer userDeliveryId) {
		this.userDeliveryId = userDeliveryId;
	}
	public Integer getUserAttendId() {
		return userAttendId;
	}
	public void setUserAttendId(Integer userAttendId) {
		this.userAttendId = userAttendId;
	}
	
	
	
	
}
