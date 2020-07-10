package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="company")
public class CompanyEntity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nombre", length=50)
	private String nombre;
	@Column(name="ruc", length=11)
	private String ruc;
	@Column(name="create_date")
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name = "user_admin_id", referencedColumnName = "id")
	private UserEntity userAdmin;
	
	@JsonIgnore
	@Column(name = "photo", updatable = false)
	private byte[] photo;
	
	@Column(name="status",length=20)
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public UserEntity getUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(UserEntity userAdmin) {
		this.userAdmin = userAdmin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
	
	

}
