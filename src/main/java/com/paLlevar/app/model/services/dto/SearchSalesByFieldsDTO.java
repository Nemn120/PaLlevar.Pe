package com.paLlevar.app.model.services.dto;

import java.time.LocalDateTime;

public class SearchSalesByFieldsDTO {
	private Integer productId;
	private Integer menuId;
	private Integer categoryId;
	private LocalDateTime initDate;
	private LocalDateTime finalDate;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
	
	
}
