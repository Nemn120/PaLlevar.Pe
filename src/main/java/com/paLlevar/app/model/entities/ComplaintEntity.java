package com.paLlevar.app.model.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="complaint")
public class ComplaintEntity extends MainEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private Integer id;
	
	@Column(name = "create_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime createDate;
	
	//@Column(name = "response_date", columnDefinition = "TIMESTAMP")
	//private LocalDateTime responseDate;
	
	@Column(name="title_complaint", length=300)
	private String titulo;
	
	//@ManyToOne
	//@JoinColumn(name="order_id",referencedColumnName="id")
	private Integer orderId;
	
	@Column(name="description_complaint")
	private String description;
	
	@JsonIgnore
	@Column(name = "photo", updatable = false)
	private byte[] photo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
	
}
