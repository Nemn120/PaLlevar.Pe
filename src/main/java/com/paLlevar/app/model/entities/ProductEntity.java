package com.paLlevar.app.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")
public class ProductEntity  extends MainEntity  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name", length=60)
	private String name;
	@Column(name="description", length=200)
	private String description;
	@Column(name="path_photo", length=100)
	private String pathPhoto;
	
	@JsonIgnore
	@Column(name = "photo", updatable = false)
	private byte[] photo;
	
	
	@ManyToOne
	@JoinColumn(name = "category_product_id", referencedColumnName = "id")
	private CategoryProductEntity categoryProduct;
	
	
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

	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public CategoryProductEntity getCategoryProduct() {
		return categoryProduct;
	}

	public void setCategoryProduct(CategoryProductEntity categoryProduct) {
		this.categoryProduct = categoryProduct;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	
}
