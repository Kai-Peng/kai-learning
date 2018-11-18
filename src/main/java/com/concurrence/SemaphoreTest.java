package com.concurrence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	private static final int THREAD_COUNT=30;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
	
	private static Semaphore s = new Semaphore(10);
	
	public static void main(String[] args) {
		for(int i=0; i<THREAD_COUNT; i++){
			threadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					try{
						//线程通过acquire获得许可
						s.acquire();
						System.out.println("save data");
						//使用完后归还许可
						s.release();
					}catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			});
		}
		threadPool.shutdown();
	}
}
