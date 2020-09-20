package com.paLlevar.app.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="menu_day")
public class MenuDayEntity  extends MainEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name="name", length=60)
	public String name;
	
	@Column(name="description", length=200)
	public String description;
	
	@Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime localDateTime;
	
	@Transient
	private LocalDateTime localDateTimeFinal;
	
	@Column(name="day", length=50)
	public String day;
	
	@OneToMany(mappedBy = "menuDay" , targetEntity = MenuDayProductEntity.class)
	private List<MenuDayProductEntity> menuDayProductList;
	
	@Column(name="type", length=20)
	private String type; // combo , paquete, menu
	
	@Column(name="total",columnDefinition = "decimal(5,2)")
	private Double total;
	
	@Column(name="count_used_menu")
	private Integer countUsedMenu;
	
	@Column(name="status")
	private String status;
	
	@Column(name="count_total")
	private Integer countTotal;
	
	public LocalDateTime getLocalDateTimeFinal() {
		return localDateTimeFinal;
	}
	public void setLocalDateTimeFinal(LocalDateTime localDateTimeFinal) {
		this.localDateTimeFinal = localDateTimeFinal;
	}
	
	public List<MenuDayProductEntity> getMenuDayProductList() {
		return menuDayProductList;
	}
	public void setMenuDayProductList(List<MenuDayProductEntity> menuDayProductList) {
		this.menuDayProductList = menuDayProductList;
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
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCountUsedMenu() {
		return countUsedMenu;
	}
	public void setCountUsedMenu(Integer countUsedMenu) {
		this.countUsedMenu = countUsedMenu;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCountTotal() {
		return countTotal;
	}
	public void setCountTotal(Integer countTotal) {
		this.countTotal = countTotal;
	}
	
	
	
	
	
	
	
}
