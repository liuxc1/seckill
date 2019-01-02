package com.liuxc.www.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.liuxc.www.domain.Product;

@Mapper
public interface IProductDao {

	List<Product> findAll();

	Product findOne(Long id);
	
	Product findOneLock(Long id);

	int update(Product product);
	
	int updateLocak(Product product);
	
}
