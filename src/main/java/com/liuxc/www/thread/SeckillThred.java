package com.liuxc.www.thread;

import com.liuxc.www.service.IProductService;

public class SeckillThred extends Thread {
	private Long id;
	private IProductService productService;
	private int i;
	public SeckillThred() {
	}

	public SeckillThred(IProductService productService,Long id,int i) {
		this.productService = productService;
		this.id=id;
		this.i=i;
	}

	@Override
	public void run() {
		System.out.println(i+"》》》开始");
		productService.seckillNotLock(id);
		System.out.println(i+"》》》结束");
	}
}
