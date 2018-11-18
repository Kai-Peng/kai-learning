package com.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioSocketClass{

	public void selector() throws IOException{
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		//调用Selector的静态工厂创建一个选择器，创建一个服务端的Channel，绑定到一个Socket对象，
		//并把这个通信信道注册到选择器上，把这个通信信道设置为非阻塞模式。
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(8080));
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true){
			//调用Selector的selectedKeys方法来检查已经注册在这个选择器上的所有通信信道是否有需要的事件发生
			Set selectedKeys = selector.selectedKeys();
			
			Iterator it = selectedKeys.iterator();
			
			while(it.hasNext()){
				SelectionKey key = (SelectionKey) it.next();
				if((key.readyOps()&SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
					ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
					SocketChannel sc = ssChannel.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
					it.remove();
				}else if((key.readyOps()&SelectionKey.OP_READ) == SelectionKey.OP_READ){
					SocketChannel sc = (SocketChannel) key.channel();
					
					while(true){
						buffer.clear();
						int n = sc.read(buffer);
						
						if(n<=0){
							break;
						}
						
						buffer.flip();
					}
					
					it.remove();
				}
			}
		}
	}
}
