package com.nio;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HashtableTest {
	
	public static void main(String[] args) {
		int h = 15;
		
		h = h^0X0000;
		
		System.out.println(h);
		
		/*ConcurrentHashMap<String, String> testCHashMap = new ConcurrentHashMap<String, String>();
		
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10000;i++){
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(),"");
						}
					},"ftf"+i).start();
				}
			}
		},"ftf");
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
