package com.synchronizedExample;

//【Demo1】：一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞
public class SyncThread implements Runnable{

	private static int count;

	public SyncThread() {
		count = 0;
	}

	public void run() {
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

	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) throws InterruptedException {
		//当两个并发线程(thread1和thread2)访问同一个对象(syncThread)中的synchronized代码块时，
		//在同一时刻只能有一个线程得到执行，另一个线程受阻塞，必须等待当前线程执行完这个代码块以后才能执行该代码块。
		//Thread1和thread2是互斥的，因为在执行synchronized代码块时会锁定当前的对象，只有执行完该代码块才能释放该对象锁，
		//下一个线程才能执行并锁定该对象
		SyncThread syncThread = new SyncThread();
		Thread thread1 = new Thread(syncThread, "SyncThread1");
		Thread thread2 = new Thread(syncThread, "SyncThread2");
		thread1.start();
		thread2.start();
		
		Thread.sleep(3000);
		
		//synchronized只锁定对象，每个对象只有一个锁（lock）与之相关联
		/*这时创建了两个SyncThread的对象syncThread3和syncThread4，线程thread1执行的是syncThread3对象中的
		synchronized代码(run)，而线程thread2执行的是syncThread4对象中的synchronized代码(run)；
		我们知道synchronized锁定的是对象，这时会有两把锁分别锁定syncThread3对象和syncThread4对象，
		而这两把锁是互不干扰的，不形成互斥，所以两个线程可以同时执行*/
		Thread thread3 = new Thread(new SyncThread(), "SyncThread3");
		Thread thread4 = new Thread(new SyncThread(), "SyncThread4");
		thread3.start();
		thread4.start();
	}
}
