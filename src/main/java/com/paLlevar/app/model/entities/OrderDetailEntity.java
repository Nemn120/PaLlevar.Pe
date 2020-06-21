package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class OrderDetailEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name="status", length=20)
	public String status;

	@Column(name = "price", columnDefinition = "decimal(5,2)")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "user_attend_id", referencedColumnName = "id")
	private UserEntity userAttend;
	@ManyToOne
	@JoinColumn(name = "user_delivery_id", referencedColumnName = "id")
	private UserEntity userDelivery;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private OrderEntity order;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public UserEntity getUserAttend() {
		return userAttend;
	}

	public void setUserAttend(UserEntity userAttend) {
		this.userAttend = userAttend;
	}

	public UserEntity getUserDelivery() {
		return userDelivery;
	}

	public void setUserDelivery(UserEntity userDelivery) {
		this.userDelivery = userDelivery;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}
	

	
}
