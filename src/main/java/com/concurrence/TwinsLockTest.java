package com.concurrence;

import java.util.concurrent.locks.Lock;

//import org.testng.annotations.Test;

public class TwinsLockTest {
//	@Test
	//public void test(){
	public static void main(String[] args) {
		final Lock lock = new TwinsLock();
		
		class Worker extends Thread{
			public void run(){
				while(true){
					lock.lock();
					try{
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName());
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}finally{
						lock.unlock();
					}
				}
			}
		}
		
		//启动10个线程
		for(int i=0;i<10;i++){
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}
		
		//每隔1秒换行
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println();
		}
	}
}
