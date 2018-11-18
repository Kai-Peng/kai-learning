package com.concurrence;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	//当多个线程视图同时执行一个任务时，只允许一个线程执行任务，其他线程需要等待这个任务完成后才能继续执行
	private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<Object, Future<String>>();
	
	private String executionTask(final String taskName) throws ExecutionException, InterruptedException{
		while(true){
			Future<String> future = taskCache.get(taskName);
			
			if(future == null){
				Callable<String> task = new Callable<String>() {
					
					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						return taskName;
					}
				};
				
				FutureTask<String> futureTask = new FutureTask<String>(task);
				future = taskCache.putIfAbsent(taskName, futureTask);
				
				if(future == null){
					future = futureTask;
					futureTask.run();
				}
			}
			
			try{
				return future.get();
			}catch (Exception e) {
				taskCache.remove(taskName,future);
			}
		}
	}
}
