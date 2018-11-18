package com.charset;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReadFileHelper {

	public void readFile(){
		String file = "c:/stream.txt";
		String charset = "UTF-8";
		
		//写字符转换成字节流
		FileOutputStream outputStream;
		OutputStreamWriter writer = null;
		try{
			outputStream = new FileOutputStream(file);
			writer = new OutputStreamWriter(outputStream, charset);
			writer.write("这是要保存的中文字符");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer!=null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		//读取字节转换成字符
		FileInputStream inputStream;
		InputStreamReader reader = null;
		StringBuffer buffer = new StringBuffer();
		char[] buf = new char[64];
		int count = 0;
		try{
			inputStream = new FileInputStream(file);
			reader = new InputStreamReader(inputStream, charset);
			while((count = reader.read(buf))!= -1){
				buffer.append(buffer, 0, count);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
