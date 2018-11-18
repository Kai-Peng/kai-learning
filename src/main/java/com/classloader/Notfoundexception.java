package com.classloader;



public class Notfoundexception {

	public static void main(String[] args) {
		try{
			Class.forName("notFoundClass");
			
//			this.getClass().getClassLoader().getResource("").toString();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
}
