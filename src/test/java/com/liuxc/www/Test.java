package com.liuxc.www;

public class Test {
	public static void main(String[] args) {
		System.out.println("主线程开始执行。。。。。");
		new Thread(()->{
			System.out.println("--子线程开始---");
			try {
				Thread.sleep(1000*3);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("--子线程结束---");
		}).start();
		System.out.println("主线程结束执行。。。。。");
	}
	
	@org.junit.Test
	public void testName() throws Exception {
		System.out.println("主线程开始执行。。。。。");
		new Thread(()->{
			System.out.println("--子线程开始---");
			try {
				Thread.sleep(1000*3);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("--子线程结束---");
		}).start();
		System.out.println("主线程结束执行。。。。。");
	}
}
