package com.paLlevar.app.model.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="profile")
public class ProfileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProfile;

	@Column(name = "name", length = 30)
	private String name;

	@Column(name = "description", length = 50)
	private String description;

	public Integer getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Integer idProfile) {
		this.idProfile = idProfile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	



}
