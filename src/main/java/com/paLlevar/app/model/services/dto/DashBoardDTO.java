package com.paLlevar.app.model.services.dto;

import java.util.List;

import com.paLlevar.app.model.entities.OrderEntity;

public class DashBoardDTO {

	private Double salesToday;
	private Double salesYesterday;
	private Double salesVariationDay;
	private Double salesThisWeek;
	private Double salesLastWeek;
	private Double salesVariationWeek;
	private Integer quantityToday;
	private Integer quantityYesterday;
	private Double quantityVariationDay;
	private Integer quantityThisWeek;
	private Integer quantityLastWeek;
	private Double quantityVariationWeek;
	
	private List<OrderEntity> orderPending;
	private List<OrderEntity> orderDelivery;
	private List<OrderEntity> orderDelivered;
	
	
	public List<OrderEntity> getOrderPending() {
		return orderPending;
	}
	public void setOrderPending(List<OrderEntity> orderPending) {
		this.orderPending = orderPending;
	}
	public List<OrderEntity> getOrderDelivery() {
		return orderDelivery;
	}
	public void setOrderDelivery(List<OrderEntity> orderDelivery) {
		this.orderDelivery = orderDelivery;
	}
	public List<OrderEntity> getOrderDelivered() {
		return orderDelivered;
	}
	public void setOrderDelivered(List<OrderEntity> orderDelivered) {
		this.orderDelivered = orderDelivered;
	}
	public Double getSalesToday() {
		return salesToday;
	}
	public void setSalesToday(Double salesToday) {
		this.salesToday = salesToday;
	}
	public Double getSalesYesterday() {
		return salesYesterday;
	}
	public void setSalesYesterday(Double salesYesterday) {
		this.salesYesterday = salesYesterday;
	}
	public Double getSalesVariationDay() {
		return salesVariationDay;
	}
	public void setSalesVariationDay(Double salesVariationDay) {
		this.salesVariationDay = salesVariationDay;
	}
	public Double getSalesThisWeek() {
		return salesThisWeek;
	}
	public void setSalesThisWeek(Double salesThisWeek) {
		this.salesThisWeek = salesThisWeek;
	}
	public Double getSalesLastWeek() {
		return salesLastWeek;
	}
	public void setSalesLastWeek(Double salesLastWeek) {
		this.salesLastWeek = salesLastWeek;
	}
	public Double getSalesVariationWeek() {
		return salesVariationWeek;
	}
	public void setSalesVariationWeek(Double salesVariationWeek) {
		this.salesVariationWeek = salesVariationWeek;
	}
	public Integer getQuantityToday() {
		return quantityToday;
	}
	public void setQuantityToday(Integer quantityToday) {
		this.quantityToday = quantityToday;
	}
	public Integer getQuantityYesterday() {
		return quantityYesterday;
	}
	public void setQuantityYesterday(Integer quantityYesterday) {
		this.quantityYesterday = quantityYesterday;
	}
	public Double getQuantityVariationDay() {
		return quantityVariationDay;
	}
	public void setQuantityVariationDay(Double quantityVariationDay) {
		this.quantityVariationDay = quantityVariationDay;
	}
	public Integer getQuantityThisWeek() {
		return quantityThisWeek;
	}
	public void setQuantityThisWeek(Integer quantityThisWeek) {
		this.quantityThisWeek = quantityThisWeek;
	}
	public Integer getQuantityLastWeek() {
		return quantityLastWeek;
	}
	public void setQuantityLastWeek(Integer quantityLastWeek) {
		this.quantityLastWeek = quantityLastWeek;
	}
	public Double getQuantityVariationWeek() {
		return quantityVariationWeek;
	}
	public void setQuantityVariationWeek(Double quantityVariationWeek) {
		this.quantityVariationWeek = quantityVariationWeek;
	}
	
	
}
