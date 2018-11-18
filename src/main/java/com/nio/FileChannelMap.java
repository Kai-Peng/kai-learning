package com.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelMap {
	
	//FileChannel.map将文件按照一定大小块映射为内存区域，当程序访问这个内存区域时将直接操作这个文件数据，
	//这种方式省去了数据从内核空间向用户空间复制的损耗。
	//适合对大文件的只读性操作
	public static void map(String[] args){
		int BUFFER_SIZE = 1024;
		String filename = "test.db";
		long fileLength = new File(filename).length();
		
		int bufferCount = 1 + (int) (fileLength / BUFFER_SIZE);
		MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCount];
		long remaining = fileLength;
		for(int i=0; i<bufferCount; i++){
			RandomAccessFile file;
			try{
				file = new RandomAccessFile(filename, "r");
				buffers[i] = file.getChannel().map(FileChannel.MapMode.READ_ONLY, i*BUFFER_SIZE, (int) Math.min(remaining, BUFFER_SIZE));
			}catch (Exception e){
				e.printStackTrace();
			}
			remaining -= BUFFER_SIZE;
		}
	}

}
