package com.hao.manager;

import java.net.Socket;
import java.rmi.server.ObjID;

import org.springframework.context.support.StaticApplicationContext;

import com.hao.communication.CommTcpImpl;
import com.hao.communication.DataRecvCallback;
import com.hao.protocol.BleProtDataStruct;
import com.hao.protocol.BleProtocol;
import com.hao.storage.DataStruct1;
import com.hao.storage.StorageIf;

public class Manager implements DataRecvCallback {

	private CommTcpImpl ctcp;
	private StorageIf sif;
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public CommTcpImpl getCtcp() {
		return ctcp;
	}

	public void setCtcp(CommTcpImpl ctcp) {
		this.ctcp = ctcp;
	}
	
	public StorageIf getSif() {
		return sif;
	}

	public void setSif(StorageIf sif) {
		this.sif = sif;
	}

	static int major = 1000;
	static int minor = 2000;
	
	public void recv(Socket socket, char[] datas, int length) {
		// TODO Auto-generated method stub
		System.out.println("recv call back: " + length);
		byte[] pktDatas = new byte[length];
		for(int i=0; i<length; i++) {
			pktDatas[i] = (byte)datas[i];
		}
		BleProtDataStruct purePktDatas = BleProtocol.parse(pktDatas);
		if(purePktDatas != null) {
			System.out.printf("valid packet:\n");
			
			for(byte[] bs : purePktDatas.bpObjects) {
				byte ObjId = bs[0];
				byte ObjDatalen = bs[1];
				System.out.printf("Object %d:%d\n", ObjId, ObjDatalen);
				if(ObjId == 1) {
					DataStruct1 ds1Insert = new DataStruct1(0,"22:33:44:55:66:01", "11:11:11:11:11:11",major,minor,-10,"-11,-9,-10");
					sif.put(ds1Insert);
					System.out.printf("insert %d,%d\n", major,minor);
					major++;
					minor++;
				}
			}
		}
	}
	
	
	public void start() {
		ctcp.start();
	}
	
}
