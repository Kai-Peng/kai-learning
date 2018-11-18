package com.concurrence;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
	
	static CountDownLatch c = new CountDownLatch(2);
	
	public static void main(String[] args) throws InterruptedException{
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(1);
				c.countDown();
				System.out.println(2);
				//c.countDown();
			}
		}).start();
		
		c.await(3,TimeUnit.SECONDS);
		System.out.println(3);
	}

}
