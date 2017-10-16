package com.hao.communication;

public interface CommIf {

	public boolean sendData(byte[] datas);
	public byte[] recvData();
}
