package com.concurrence;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ReentrantLock1 extends AbstractQueuedSynchronizer{
	int a = 0;
	ReentrantLock1 lock = new ReentrantLock1();
	
	public void writer(){
		lock.lock();//获取锁
		try{
			a++;
		}finally{
			lock.unlock();//释放锁
		}
	}
	
	public void reader(){
		lock.lock();//获取锁
		try{
			int i =a;
		}finally{
			lock.unlock();//释放锁
		}
	}
	
	public void lock(){
		
	}
	
	public void unlock(){
		
	}
	
	protected final boolean tryAcquire(int acquires){
		final Thread current = Thread.currentThread();
		int c = getState(); //获取锁的开始，首先读volatile变量state
		if(c==0){
			if(/*isFirst(current)&&*/compareAndSetState(0, acquires)){
				setExclusiveOwnerThread(current);
				return true;
			}
		}else if(current==getExclusiveOwnerThread()){
			int nextc = c+acquires;
			if(nextc<0){
				throw new Error("Maximum lock count exceeded");
			}
			setState(nextc);
			return true;
		}
		
		return false;
	}
	
	protected final boolean tryRelease(int releases) {       
		int c = getState() - releases;       
		if (Thread.currentThread() != getExclusiveOwnerThread()) throw new IllegalMonitorStateException();       
		boolean free = false;
		if (c == 0) {           
			free = true;           
			setExclusiveOwnerThread(null);       
		}       
		setState(c);// 释放锁的最后，写volatile变量state       
		return free;
	}	
}
