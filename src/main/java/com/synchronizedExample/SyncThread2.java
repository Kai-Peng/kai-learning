package com.synchronizedExample;

//【Demo2】：当一个线程访问对象的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块
public class SyncThread2 implements Runnable {
	private int count;

	public SyncThread2() {
		count = 0;
	}

	public void countAdd() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
	public void printCount() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " count:" + count);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		String threadName = Thread.currentThread().getName();
		if (threadName.equals("A")) {
			countAdd();
		} else if (threadName.equals("B")) {
			printCount();
		}
	}
	
	public static void main(String[] args) {
		//上面代码中countAdd是一个synchronized的，printCount是非synchronized的。从上面的结果中可以看出一个线程
		//访问一个对象的synchronized代码块时，别的线程可以访问该对象的非synchronized代码块而不受阻塞。
		SyncThread2 syncThread2 = new SyncThread2();
		Thread thread1 = new Thread(syncThread2, "A");
		Thread thread2 = new Thread(syncThread2, "B");
		thread1.start();
		thread2.start();
	}
}
