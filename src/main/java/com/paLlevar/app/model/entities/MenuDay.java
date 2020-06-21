package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="menu_day")
public class MenuDay implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@Column(name="name", length=60)
	public String name;
	@Column(name="description", length=200)
	public String description;
	
	public Date date;
	@Column(name="day", length=50)
	public String day;
	
	@OneToMany(mappedBy = "menuDay" , targetEntity = MenuDayProduct.class)
	private List<MenuDayProduct> menuDayProduct;
	
	
	public List<MenuDayProduct> getMenuDayProduct() {
		return menuDayProduct;
	}
	public void setMenuDayProduct(List<MenuDayProduct> menuDayProduct) {
		this.menuDayProduct = menuDayProduct;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
	
}
