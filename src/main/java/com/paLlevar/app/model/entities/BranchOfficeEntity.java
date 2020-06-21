package com.paLlevar.app.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="branch_office")
public class BranchOfficeEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nombre", length=200)
	private String nombre;
	@Column(name="direccion", length=200)
	private String direccion;
	@Column(name="phone", length=20)
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "user_admin_id", referencedColumnName = "id")
	private UserEntity userAdmin;

	
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserEntity getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(UserEntity userAdmin) {
		this.userAdmin = userAdmin;
	}

}
