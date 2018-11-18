package com.concurrence;

import java.util.ArrayList;
import java.util.List;

public class MonitorExample {

	int a = 0;
	
	public synchronized void writer(){
		a++;
	}
	
	public synchronized int reader(){
		return a;
	}
	
	public int getA(){
		return a;
	}
	
	public static void main(String[] args) {
		final MonitorExample mExam = new MonitorExample();
		List treadpools = new ArrayList();
		for (int j = 0; j < 100; j++) { 
			Thread treadpool = new Thread(new Runnable() {
				@Override
				public void run() {
					mExam.writer();
					System.out.println("writer"+mExam.getA());
				}
			});
			treadpools.add(treadpool);
			
			Thread treadpool1 = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("mExam.reader()"+mExam.reader());
					System.out.println("reader"+mExam.getA());
				}
			});
			treadpools.add(treadpool1);
		}
		
		for(Object exam: treadpools){
			Thread examThread = (Thread) exam;
			examThread.start();
		}
		
		//等待所有线程执行完成
		for(Object exam: treadpools){
			Thread examThread = (Thread) exam;
			try{
				examThread.join();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
}
