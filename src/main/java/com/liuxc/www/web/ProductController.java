package com.liuxc.www.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuxc.www.service.IProductService;
import com.liuxc.www.thread.SeckillThred;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private IProductService productService;

	@RequestMapping("/seckill")
	public Object seckill(Long id) {
		/*int i = productService.seckillNotLock(id);
		System.out.println(i);*/
		for (int i = 0; i < 100; i++) {
			//SeckillThred seckillThred = new SeckillThred(productService,2L);
			//seckillThred.start();
		}
		return true;
	}

}
