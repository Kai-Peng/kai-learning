package com.concurrence;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
	public class CountTask extends RecursiveTask<Long>{
		private static final long THRESHOLD = 2;
		private long start;
		private long end;
		
		public CountTask(long start, long end){
			this.start = start;
			this.end = end;
		}
		
		@Override
		protected Long compute(){
			long sum = 0;
			//如果任务足够小就计算任务
			boolean canCompute = (end-start)<=THRESHOLD;
			if(canCompute){
				for(long i=start; i<=end; i++){
					sum+=i;
				}
			}else{
				//如果任务大于阈值，就分裂成两个子任务计算
				long middle = (start+end)/2;
				CountTask leftTask = new CountTask(start, middle);
				CountTask rightTask = new CountTask(middle+1, end);
				//执行子任务
				leftTask.fork();
				rightTask.fork();
				
				//等待子任务执行完，并得到其结果
				long leftResult = leftTask.join();
				long rightResult = rightTask.join();
				//合并子任务
				sum = leftResult+rightResult;
			}
			return sum;
		}
	}
	
	public static void main(String[] args) {
		
		long maxNum = 5000;
		
		long startNanoTime1 = System.nanoTime();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
		ForkJoinTest forkJoinTest = new ForkJoinTest();
		
		//生成一个计算任务，负责计算1+2+3+4
		CountTask task = forkJoinTest.new CountTask(1, maxNum);
		
		//执行一个任务
		Future<Long> result = forkJoinPool.submit(task);
		
		if(task.isCompletedAbnormally()){
			System.out.println(task.getException());
			return;
		}
		
		try{
			System.out.println("result1:"+result.get());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("waste time long about FORK/JOIN:"+(System.nanoTime()-startNanoTime1));
		
		long startNanoTime2 = System.nanoTime();
		long sum = 0;
		for(long i=1;i<=maxNum;i++){
			sum += i;
		}
		System.out.println("result2:"+sum);
		System.out.println("waste time long about single thread:"+(System.nanoTime()-startNanoTime2));
	}
}
