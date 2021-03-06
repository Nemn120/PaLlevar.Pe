package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "order_header")
public class OrderEntity    extends MainEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name="status", length=200)
	public String status; // en proceso, entregado, 

	@Column(name = "total", columnDefinition = "decimal(5,2)")
	private Double total;
	
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "user_order_id", referencedColumnName = "id")
	private UserEntity userOrder;
	
	@OneToMany(mappedBy = "order" , targetEntity = OrderDetailEntity.class)
	private List<OrderDetailEntity> orderDetail;
	
	@Column(name="delivery_date")
	private Date deliveryDate;
	
	@Column(name="attend_date")
	private Date attendDate;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="user_delivery_id")
	private Integer userDeliveryId;
	
	@Column(name="reference")
	private String reference;
	
	@Transient
	private Integer userAttendId;
	
	@Transient
	private UserEntity userDelivery;
	
	@Column(name="company_name")
	private String companyName;
	
	@ManyToOne
	@JoinColumn(name = "place_id", referencedColumnName = "id")
	private PlaceEntity place;
	
	@Column(name="delivered_date")
	private Date deliveredDate;
	
	public List<OrderDetailEntity> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetailEntity> orderDetail) {
		this.orderDetail = orderDetail;
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
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}
	public UserEntity getUserOrder() {
		return userOrder;
	}
	public void setUserOrder(UserEntity userOrder) {
		this.userOrder = userOrder;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public PlaceEntity getPlace() {
		return place;
	}
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}
	public UserEntity getUserDelivery() {
		return userDelivery;
	}
	public void setUserDelivery(UserEntity userDelivery) {
		this.userDelivery = userDelivery;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getDeliveredDate() {
		return deliveredDate;
	}
	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}
}
