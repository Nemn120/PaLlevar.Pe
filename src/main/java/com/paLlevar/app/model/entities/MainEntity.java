package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class MainEntity implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private Integer userCreateId;
	private Integer organizationId;
	private Integer sucursalId;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUserCreateId() {
		return userCreateId;
	}
	public void setUserCreateId(Integer userCreateId) {
		this.userCreateId = userCreateId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	
	
	
	
	 

}
