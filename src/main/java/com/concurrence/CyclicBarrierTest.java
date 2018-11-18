package com.concurrence;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
	static CyclicBarrier c = new CyclicBarrier(3);
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					c.await();
				}catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println(1);
			}
		}).start();
		
		try{
			c.await();
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(2);
	}
	
	static CyclicBarrier c2 = new CyclicBarrier(2, new A());
	
	static class A implements Runnable{
		@Override
		public void run(){
			System.out.println(3);
		}
	}
	
}
