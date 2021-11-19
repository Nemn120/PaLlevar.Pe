package com.paLlevar.app.model.dto;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class MainDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Column(name = "create_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime createDate;
	private Integer userCreateId;
	private Integer organizationId;
	private Integer sucursalId;
	
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
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
