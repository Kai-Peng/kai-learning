package com.cookies;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class CookiesCompress {
	private void compressCookie(Cookie c, HttpServletResponse res){
		try{
			ByteArrayOutputStream bos = null;
			bos = new ByteArrayOutputStream();	
			DeflaterOutputStream dos = new DeflaterOutputStream(bos);
			
			dos.write(c.getValue().getBytes());
			dos.close();
			System.out.println("before compress length:"+ c.getValue().getBytes().length);
			
			String compress = new sun.misc.BASE64Encoder().encode(bos.toByteArray());
			res.addCookie(new Cookie("compress", compress));
			System.out.println("after compress length:"+compress.getBytes().length);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void unCompressCookie(Cookie c){
		try{
			ByteArrayOutputStream out = new ByteArrayOutputStream();	
			byte[] compress = new sun.misc.BASE64Decoder().decodeBuffer(new String(c.getValue().getBytes()));
			
			ByteArrayInputStream bis = new ByteArrayInputStream(compress);
			InflaterInputStream inflater = new InflaterInputStream(bis);
			
			byte[] b = new byte[1024];
			int count;
			while((count = inflater.read(b))>=0){
				out.write(b,0,count);
			}
			
			inflater.close();
			
			System.out.println(out.toByteArray());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
