package com.paLlevar.app.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="profile_menu_option")
public class ProfileMenuOptionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMenuRol;
	
	@ManyToOne
	@JoinColumn(name = "id_profile", referencedColumnName = "idProfile")
	private ProfileEntity profile;

	@ManyToOne
	@JoinColumn(name = "id_menu", referencedColumnName = "idMenu")
	private MenuOptionEntity menu;

	public Integer getIdMenuRol() {
		return idMenuRol;
	}

	public void setIdMenuRol(Integer idMenuRol) {
		this.idMenuRol = idMenuRol;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	public MenuOptionEntity getMenu() {
		return menu;
	}

	public void setMenu(MenuOptionEntity menu) {
		this.menu = menu;
	}
	
	
}
