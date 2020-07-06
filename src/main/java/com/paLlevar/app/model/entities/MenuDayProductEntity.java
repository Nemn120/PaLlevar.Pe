package com.paLlevar.app.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="menu_product_day")
public class MenuDayProductEntity   extends MainEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private ProductEntity product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "menu_day_id", referencedColumnName = "id")
	private MenuDayEntity menuDay;
	
	@Column(name = "price", columnDefinition = "decimal(5,2)")
	private Double price;
	
	private Integer quantity;
	private Integer available;
	@Column(name="status", length=20)
	private String status; // disponible , no disponible
	
	@Column(name="type", length=20)
	private String type; 	// combo ,men , paquete
	
	@Transient
	private Integer menuDayId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}
	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	public MenuDayEntity getMenuDay() {
		return menuDay;
	}
	public void setMenuDay(MenuDayEntity menuDay) {
		this.menuDay = menuDay;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getMenuDayId() {
		return menuDayId;
	}
	public void setMenuDayId(Integer menuDayId) {
		this.menuDayId = menuDayId;
	}
	
	
	
	
	
}
