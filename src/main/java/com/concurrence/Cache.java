package com.concurrence;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Cache {
	//Hashtable不支持null值作为key，线程安全
	static Hashtable testT = new Hashtable();
	
	static Map<String,Object> map = new HashMap<String, Object>();

	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	static Lock r = rwl.readLock();
	
	static Lock w = rwl.writeLock();
	
	//获取一个key对应的value
	public static final Object get(String key){
		r.lock();
		try{
			return map.get(key);
		}finally{
			r.unlock();
		}
	}
	
	//设置key对应的value，并返回旧的value
	public static final Object put(String key, Object value){
		w.lock();
		try{
			return map.put(key, value);
		}finally{
			w.unlock();
		}
	}
	
	//清空所有的内容
	public static final void clear(){
		w.lock();
		try{
			map.clear();
		}finally{
			w.unlock();
		}
	}
	
	public static void main(String[] args) {
		int i = 4 ;
		i>>>=16;

		System.out.println("test");
		System.out.println(i);
	}
}
