package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class ProfileEntity   extends MainEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="short_description",length=30)
	private String shortDescription;
	@Column(name="long_description",length=60)
	private String longDescription;
	//private String type;
	
	@OneToMany(mappedBy = "profile",targetEntity = ProfileMenuOptionEntity.class)
	private List<ProfileMenuOptionEntity> listProfileMenuOption;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public List<ProfileMenuOptionEntity> getListProfileMenuOption() {
		return listProfileMenuOption;
	}

	public void setListProfileMenuOption(List<ProfileMenuOptionEntity> listProfileMenuOption) {
		this.listProfileMenuOption = listProfileMenuOption;
	}
	
	
	

}
