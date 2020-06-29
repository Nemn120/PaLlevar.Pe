package com.paLlevar.app.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="profile_menu_option")
public class ProfileMenuOptionEntity  extends MainEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name= "profile_id", referencedColumnName = "id")
	private ProfileEntity profile;
	
	@ManyToOne
	@JoinColumn(name="menu_option_id", referencedColumnName = "id")
	private MenuOptionEntity menuOption;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	public MenuOptionEntity getMenuOption() {
		return menuOption;
	}

	public void setMenuOption(MenuOptionEntity menuOption) {
		this.menuOption = menuOption;
	}


	

}
