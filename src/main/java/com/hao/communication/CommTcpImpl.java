package com.hao.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


class ServerThread implements Runnable{
	
	private Socket client = null;
	private DataRecvCallback recvCallback = null;
	
	public ServerThread(Socket client, DataRecvCallback recvCallback) {
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
			int recvLen = 0;
			char[] cbuf = new char[1024];
			while(flag&&(recvLen>=0)) {
				//接收从客户端发送过来的数据
				recvLen = buf.read(cbuf, 0, cbuf.length);
				if(recvLen>0) {
					System.out.printf("recv: ");
					for(int i=0; i<recvLen; i++) {
						System.out.printf("%02x ", Integer.valueOf(cbuf[i]));
					}
					System.out.println("");
				}
				if(recvCallback != null) {
					recvCallback.recv(client, cbuf, recvLen);
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


public class CommTcpImpl implements CommIf, Runnable{

	private ServerSocket server = null;
	private List<Socket> clientList = null;
	private int port;
	private DataRecvCallback recvCallback = null;
	
	public CommTcpImpl(){
	}


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public DataRecvCallback getRecvCallback() {
		return recvCallback;
	}

	public void setRecvCallback(DataRecvCallback recvCallback) {
		this.recvCallback = recvCallback;
	}


	public void start() {
		try {
			server = new ServerSocket(port);
			clientList = new ArrayList<Socket>();
			
			new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			new Thread(new ServerThread(client, this.recvCallback)).start();
		}
		
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean sendData(Object obj, byte[] datas) {
		// TODO Auto-generated method stub
		Socket socket = (Socket)obj;
		return false;
	}

	public byte[] recvData(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
