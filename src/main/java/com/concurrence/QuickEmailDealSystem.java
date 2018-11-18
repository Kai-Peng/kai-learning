package com.concurrence;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.*;

public class QuickEmailDealSystem {
	private Logger logger = LoggerFactory.getLogger(QuickEmailDealSystem.class);
	
	private ThreadPoolExecutor threadsPool;
	
	private static ArrayList<DataTestDto> dataTestArray;
	
	class DataTestDto{
		private String userId;
		private String userName;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
	}
	
	
	public QuickEmailDealSystem(){
		dataTestArray = new ArrayList<QuickEmailDealSystem.DataTestDto>();
		
		int corePoolSize = Runtime.getRuntime().availableProcessors()*2;
		
		threadsPool = new ThreadPoolExecutor(corePoolSize,corePoolSize,10l,TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(2000));
	}
	
	public void dealMethod(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("test");
				
				if(dataTestArray!=null){
					for(DataTestDto dateTest : dataTestArray){
					}
				}
			}
		}).start();
		
		
	}
	
}
