package com.liuxc.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuxc.www.dao.IProductDao;
import com.liuxc.www.domain.Product;
import com.liuxc.www.service.IProductService;
/**
 * 
 * @author L
 *
 */
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao productDao;
	
	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Product findOne(Long id) {

		try {
			return productDao.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Product findOneLock(Long id) {
		
		return productDao.findOneLock(id);
	}

	@Override
	public int update(Product product) {

		return productDao.update(product);
	}

	/**
	 * 秒杀业务 --不加锁 - 并发
	 */
	@Override
	public int seckillNotLock(Long id) {
		int i = 0;
		Product product = this.findOne(id);
		if (product != null && product.getNum() > 0) {
			i = productDao.update(product);
		}
		
		return i;
	}
	
	/**
	 * 秒杀业务 --应用层加锁 - 悲观锁
	 */
	@Override
	public synchronized int seckillPessimisticLock(Long id) {
		int i = 0;
		Product product = this.findOne(id);
		if (product != null && product.getNum() > 0) {
			i = productDao.update(product);
		}
		return i;
	}
	
	/**
	 * 秒杀业务 --数据库加锁 - 悲观锁
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public  int seckillPessimisticLockByDataBase(Long id) {
		int i = 0;
		Product product = this.findOneLock(id);
		if (product != null && product.getNum() > 0) {
			i = productDao.update(product);
		}
		return i;
	}
	
	/**
	 * 秒杀业务 --数据库加锁 - 乐观锁
	 */
	@Override
	public  int seckillOptimisticLockByDataBase(Long id) {
		int i = 0;
		Product product = productDao.findOne(id);
		if (product != null && product.getNum() > 0) {
			i = productDao.updateLocak(product);
		}
		return i;
	}

}
