package com.hao.protocol;

public class ProtCommon {

	public static byte calChksum(byte[] datas, int start, int end) {
		
		if((end < start)||(datas.length < (end-start))) {
			return 0;
		}
		
		byte chksum = 0;
		for(int i=start; i<end; i++) {
			chksum += datas[i];
		}
		return chksum;
	}
}
