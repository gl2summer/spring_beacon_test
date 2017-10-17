package com.hao.protocol;

import java.util.List;

public class BleProtDataStruct {

	public byte bpPrefix;
	public byte bpLength;
	public byte bpControl;
	public byte bpChksum;
	public byte bpSuffix;
	
	public List<byte[]> bpObjects;
}
