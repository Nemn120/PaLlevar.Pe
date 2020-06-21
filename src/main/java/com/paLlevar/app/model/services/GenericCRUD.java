package com.paLlevar.app.model.services;

import java.util.List;

public interface GenericCRUD<T,I> {
	public List<T > getAll();
	public T getOneById(I id);
	public T save(T t);
	public void deleteById(I id);

}
