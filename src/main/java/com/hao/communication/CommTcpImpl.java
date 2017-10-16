package com.hao.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


interface RecvCallback{
	public void recv(char[] datas);
}

class ServerThread implements Runnable{
	
	private Socket client = null;
	private RecvCallback recvCallback = null;
	
	public ServerThread(Socket client, RecvCallback recvCallback) {
		super();
		this.client = client;
		this.recvCallback = recvCallback;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			//获取Socket的输出流，用来向客户端发送数据
			PrintStream out = new PrintStream(client.getOutputStream());
			//获取Socket的输入流，用来接收从客户端发送过来的数据
			BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
			boolean flag = true;
			char[] cbuf = new char[1024];
			while(flag) {
				//接收从客户端发送过来的数据
				int recvLen = buf.read(cbuf, 0, cbuf.length);
				if(recvCallback != null) {
					recvCallback.recv(cbuf);
				}
				if(recvLen>0) {
					System.out.printf("recv: ");
					for(int i=0; i<recvLen; i++) {
						System.out.printf("%02x ", Integer.valueOf(cbuf[i]));
					}
					System.out.println("");
				}
			}
			out.close();
			client.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}


public class CommTcpImpl implements CommIf, Runnable,RecvCallback{

	private ServerSocket server = null;
	private List<Socket> clientList = null;
	
	public CommTcpImpl(int port){
		
		try {
			server = new ServerSocket(port);
			clientList = new ArrayList<Socket>();
			
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recv(char[] datas) {
		// TODO Auto-generated method stub
		System.out.println("recv call back");
	}


	public void run() {
		// TODO Auto-generated method stub
		
		Socket client = null;
		boolean flag = true;
		while(flag) {
			try {
				client = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("accept");
			clientList.add(client);
			new Thread(new ServerThread(client,this)).start();
		}
		
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean sendData(byte[] datas) {
		// TODO Auto-generated method stub
		return false;
	}

	public byte[] recvData() {
		// TODO Auto-generated method stub
		return null;
	}

}
