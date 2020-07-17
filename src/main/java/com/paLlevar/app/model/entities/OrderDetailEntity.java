package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="order_detail")
public class OrderDetailEntity extends MainEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name="status", length=20)
	public String status; // atendido, entregado , en camino 

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
	private ProductEntity product;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private OrderEntity order;
	
	@Transient	// no se mapea a la bd
	private Integer orderId;
	
	@Column(name="menu_product_id")
	private Integer menuProductId;
	
	@Column(name="delivery_date")
	private Date deliveryDate;
	
	@Column(name="attend_date")
	private Date AttendDate;
	
	@Column(name="company_name")
	private String companyName;
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMenuProductId() {
		return menuProductId;
	}

	public void setMenuProductId(Integer menuProductId) {
		this.menuProductId = menuProductId;
	}

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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getAttendDate() {
		return AttendDate;
	}

	public void setAttendDate(Date attendDate) {
		AttendDate = attendDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	

	
}
