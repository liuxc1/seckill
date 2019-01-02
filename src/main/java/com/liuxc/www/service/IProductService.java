package com.liuxc.www.service;

import java.util.List;

import com.liuxc.www.domain.Product;

public interface IProductService {
	List<Product> findAll();
	
	Product findOne(Long id);
	
	Product findOneLock(Long id);

	int update(Product product);

	/**
	 * 秒杀场景
	 */
	int seckillNotLock(Long id);
	
	/**
	 * 悲观锁
	 */
	int seckillPessimisticLock(Long id);
	
	int seckillPessimisticLockByDataBase(Long id);
	
	/**
	 * 乐观锁
	 */
	int seckillOptimisticLockByDataBase(Long id);
}
