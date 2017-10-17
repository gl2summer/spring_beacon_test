package com.hao.communication;

public interface CommIf {

	public boolean sendData(Object obj, byte[] datas);
	public byte[] recvData(Object obj);
}
