package com.concurrence;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import sun.misc.Unsafe;

public class AtomicTest {

	public static void main(String[] args) {
		AtomicInteger aInt = new AtomicInteger(3);
		aInt.addAndGet(3);
		
		aInt.compareAndSet(6, 8);
		
		System.out.println(aInt);
		
		aInt.compareAndSet(7, 9);
		
		System.out.println(aInt);
		
		
		System.out.println(aInt.getAndIncrement());
		
		System.out.println(aInt);

		//原子更新类对象中的属性
		AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
		 
		User conan = new User("conan", 10);
		
		System.out.println(a.getAndIncrement(conan));
		System.out.println(a.get(conan));
	}
	
	public static class User{
		private String name;
		public volatile int old;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getOld() {
			return old;
		}
		public void setOld(int old) {
			this.old = old;
		}
		
		public User(String name, int old) {
			this.name = name;
			this.old = old;
		}
	}
}
