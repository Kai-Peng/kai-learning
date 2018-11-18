package com.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class ClassReloader extends ClassLoader{
	private String classPath;
	String classname = "compile.Yufa";
	
	public ClassReloader(String classPath){
		this.classPath = classPath;
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException{
		byte[] classData = getData(name);
		if(classData == null){
			throw new ClassNotFoundException();
		}else{
			return defineClass(classname, classData, 0, classData.length);
		}
	}
	
	private byte[] getData(String className){
		String path = classPath + className;
		try{
			InputStream is = new FileInputStream(path);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[2048];
			int num = 0;
			while((num = is.read(buffer)) != -1){
				stream.write(buffer, 0, num);
			}
			return stream.toByteArray();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try{
			String path = "D:/devtools/compile/target/classes/compile/";
			ClassReloader reloader = new ClassReloader(path);
			Class r = reloader.findClass("Yufa.class");
			System.out.println(r.newInstance());
			
			ClassReloader reloader2 = new ClassReloader(path);
			Class r2 = reloader2.findClass("Yufa.class");
			System.out.println(r2.newInstance());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
