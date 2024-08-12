package com.fcmanagement.generic;
import java.util.List;

public interface GenericService<T extends Object> {

	T save(T entity);
	
	T find(Integer id);
    
    T update(T entity);
  
    void delete(T entity);
  
    List<T> findAll();
}