package com.liuxc.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.liuxc.www.domain.Product;
import com.liuxc.www.service.IProductService;

@SpringBootTest(classes = com.liuxc.www.StartSpringBootMain.class)
@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SeckillTest {

	@Autowired
	private  IProductService productService;

	@Test
	public void findAll() throws Exception {
		List<Product> list = productService.findAll();
		list.forEach(System.out::println);
	}

	@Test
	public void findOne() throws Exception {
		Product product = productService.findOne(2L);
		System.out.println(product);
	}

	@Test
	public void update() throws Exception {
		Product product = productService.findOne(2L);
		int i = productService.update(product);
		System.out.println(i);
	}

	/**
	 * 100 并发秒杀测试，无锁
	 * 
	 * @throws Exception
	 */
	@Test
	public void seckillNotLock() throws Exception {
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(()->{
				productService.seckillNotLock(2L);
			});
			thread.start();
			if(i==99){
				thread.join();
			}
		}
	}
	
	/**
	 * 加同步锁关键字
	 */
	@Test
	public void seckillPessimisticLock() throws Exception {
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(()->{
				productService.seckillPessimisticLock(2L);
			});
			thread.start();
		}
		Thread.sleep(1000*5);
	}
	
	/**
	 *	数据库加锁-悲观锁
	 */
	@Test
	public void seckillPessimisticLockByDataBase() throws Exception {
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(()->{
				productService.seckillPessimisticLockByDataBase(2L);
			});
			thread.start();
		}
		Thread.sleep(1000*5);
	}
	
	/**
	 *	数据库加锁-乐观锁
	 */
	@Test
	public void seckillOptimisticLockByDataBase() throws Exception {
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(()->{
				int j = productService.seckillOptimisticLockByDataBase(2L);
				if(j>0){
					System.out.println("秒杀成功！");
				}
			});
			thread.start();
		}
		Thread.sleep(1000*5);
	}
	
}
