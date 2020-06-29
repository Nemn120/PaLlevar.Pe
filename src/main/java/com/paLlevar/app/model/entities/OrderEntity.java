package com.paLlevar.app.model.entities;

import java.io.Serializable;
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
	public String status;

	@Column(name = "total", columnDefinition = "decimal(5,2)")
	private Double total;
	
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "client_order_id", referencedColumnName = "id")
	private ClientEntity clientOrder;
	
	@OneToMany(mappedBy = "order" , targetEntity = OrderDetailEntity.class)
	private List<OrderDetailEntity> orderDetail;
	
	
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
	public ClientEntity getClientOrder() {
		return clientOrder;
	}
	public void setClientOrder(ClientEntity clientOrder) {
		this.clientOrder = clientOrder;
	}
	

}
