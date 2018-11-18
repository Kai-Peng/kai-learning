package com.concurrence;

import java.util.concurrent.TimeUnit;

public class Interrupted {
	
	public static void main(String[] args) throws Exception{
		//sleepThread不停的尝试睡眠
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		//busyThread不停的运行
		Thread busyThread = new Thread(new BusyRunner(),"BusyThread");
		busyThread.setDaemon(true);
		sleepThread.start();
		busyThread.start();
		//休眠5秒，让sleepThread和busyThread充分运行
		TimeUnit.SECONDS.sleep(5);
		sleepThread.interrupt();
		busyThread.interrupt();
		System.out.println("SleepThread interrupted is "+sleepThread.isInterrupted());
		System.out.println("BusyThread interrupted is "+busyThread.isInterrupted());
		//防止sleepThread和busyThread立刻送出
		Thread.sleep(2000);
	}

	static class SleepRunner implements Runnable {        
		@Override        
		public void run() {            
			while (true) {                
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}          
			}        
		}    
	}
	
	static class BusyRunner implements Runnable {        
		@Override        
		public void run() {            
			while (true) {                
			}        
		}    
	}
}
