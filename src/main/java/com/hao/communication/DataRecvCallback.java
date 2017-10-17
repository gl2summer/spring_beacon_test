package com.hao.communication;

import java.net.Socket;

public interface DataRecvCallback {
	
	public void recv(Socket socket, char[] datas, int length);
}
