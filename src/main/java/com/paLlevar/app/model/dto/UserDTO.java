package com.paLlevar.app.model.dto;

import java.io.Serializable;
import java.util.Date;


public class UserDTO extends MainDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String password;
	private String status;
	private String address;
	private String cellPhone;
	private String username;
	private String employeeCode;
	private String documentTypeId;
	private String documentNumber;
	private Date dateBirth;

	private String lastName;

	private byte[] photo;

	private ProfileDTO profile;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}

	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
