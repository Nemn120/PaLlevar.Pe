package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="menu_option")
public class MenuOptionEntity  extends MainEntity  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	@Column(name="level_menu")
	private Integer levelMenu;
	@Column(name="url_menu")
	private String urlMenu;
	private String icon;
	@Column(name="order_menu")
	private Integer orderMenu;
	
	@OneToMany(mappedBy="menuOption", targetEntity = ProfileMenuOptionEntity.class)
	private List<ProfileMenuOptionEntity> listProfileMenu;
	
	@OneToMany(mappedBy="fatherMenuOption", targetEntity = MenuOptionEntity.class)
	private List<MenuOptionEntity> listMenuOption;
	
	@ManyToOne
	@JoinColumn(name = "father_menu_option_id", referencedColumnName = "id")
	private MenuOptionEntity fatherMenuOption;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlMenu() {
		return urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<ProfileMenuOptionEntity> getListProfileMenu() {
		return listProfileMenu;
	}

	public void setListProfileMenu(List<ProfileMenuOptionEntity> listProfileMenu) {
		this.listProfileMenu = listProfileMenu;
	}

	public List<MenuOptionEntity> getListMenuOption() {
		return listMenuOption;
	}

	public void setListMenuOption(List<MenuOptionEntity> listMenuOption) {
		this.listMenuOption = listMenuOption;
	}

	public MenuOptionEntity getFatherMenuOption() {
		return fatherMenuOption;
	}

	public void setFatherMenuOption(MenuOptionEntity fatherMenuOption) {
		this.fatherMenuOption = fatherMenuOption;
	}

	public Integer getLevelMenu() {
		return levelMenu;
	}

	public void setLevelMenu(Integer levelMenu) {
		this.levelMenu = levelMenu;
	}

	public Integer getOrderMenu() {
		return orderMenu;
	}

	public void setOrderMenu(Integer orderMenu) {
		this.orderMenu = orderMenu;
	}
	

}
