package com.paLlevar.app.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.ProductEntity;

@Service
public interface CategoryProductService extends GenericCRUD<CategoryProductEntity, Integer>{
	List<CategoryProductEntity> getListCategoryProductByOrgId(Integer organizationId);
}
