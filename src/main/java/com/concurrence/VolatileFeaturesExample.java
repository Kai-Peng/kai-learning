package com.concurrence;

public class VolatileFeaturesExample {

	volatile long vl = 0L;//使用volatile声明64位的long型变量     
	public void set(long l) {           
		vl = l;// 单个volatile变量的写       
	}
	
	public void getAndIncrement(){
		vl++;//复合（多个）volatile变量的读/写
	}
	
	public long get(){
		return vl;//单个volatile变量的读
	}
	
	
	//下面的同步效果与上面的代码一致
	long v2 = 0L;//64位的long型普通变量
	
	public synchronized void set2(long l){
		//对单个的普通变量的写用同一个锁同步
		v2 = 1;
	}
	
	public void getAndIncrement2(){
		//普通方法调用
		long temp = get2();//调用已同步的读方法
		temp+=1L;//普通写操作
		set2(temp);//调用已同步的写方法
	}
	
	public synchronized long get2(){
		//对单个的普通变量的读用同一个锁同步
		return v2;
	}
}
