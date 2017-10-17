package com.hao.protocol;

import java.util.ArrayList;
import java.util.List;


public class BleProtocol {

	//报文方向
	public final static byte DIR_SET = 0;
	public final static byte DIR_GET = 1;
	public final static byte DIR_RPT = 2;
	public final static byte DIR_ACK = 3;
	public final static byte DIR_ERR = 4;
	
	//错误代码
	public final static byte ERR_SUCCESS  = 0;
	public final static byte ERR_FAILED   = 1;
	public final static byte ERR_OVERFLOW = 2;
	public final static byte ERR_OTHER    = (byte) 0xFE;
	
	//前缀，后缀
	public final static byte PREFIX = 0x58;
	public final static byte SUFFIX = 0x4D;
	
	
	private static boolean findPktFlag(byte[] pakectDatas, int start, int[] pktStartIdx, int[] pktEndIdx) {
		if(start >= pakectDatas.length) {
			return false;
		}
		
		int prefixIdx;
		for(prefixIdx=start; prefixIdx<pakectDatas.length; prefixIdx++) {
			if(pakectDatas[prefixIdx] == PREFIX) {
				pktStartIdx[0] = prefixIdx;
				break;
			}
		}
		
		if(prefixIdx >= pakectDatas.length) {
			return false;
		}
		
		int suffixIdx;
		for(suffixIdx=prefixIdx+1; suffixIdx<pakectDatas.length; suffixIdx++) {
			if(pakectDatas[suffixIdx] == SUFFIX) {
				pktEndIdx[0] = suffixIdx;
				break;
			}
		}
		return (suffixIdx<pakectDatas.length);
	}
	
	private static boolean checkPktValid(byte[] pakectDatas, int start, int end, BleProtDataStruct protDataStruct) {
		if((start > end)||(start > pakectDatas.length)||(end > pakectDatas.length)) {
			return false;
		}
		int index = start;//point to 'prefix'
		byte prefix = pakectDatas[index];
		if(prefix != PREFIX) {
			return false;
		}
		
		index++;//point to 'length'
		byte pktLen = pakectDatas[index];
		if(pktLen < 2) {
			return false;
		}
		if((end-start+1) != (pktLen+3)) {
			return false;
		}
		
		index++;//point to 'length'
		byte ctrl = pakectDatas[index];
		
		index++;//point to 'objects'
		int objIndex = index;
		int objTotalLen = pktLen-2;
		if(objTotalLen < 2) {
			return false;
		}
		List<byte[]> objList = new ArrayList<byte[]>();
		while(objTotalLen>0) {
			byte objLen = pakectDatas[objIndex+1];
			if(objLen+2 > objTotalLen) {
				return false;
			}
			byte[] objBytes = new byte[objLen+2];
			System.arraycopy(pakectDatas, objIndex, objBytes, 0, objLen+2);//copy one object
			objList.add(objBytes);
			objIndex += (objLen+2);
			objTotalLen -= (objLen+2);
		}
		
		index += (pktLen-2);//point to 'checksum'
		byte chksum = pakectDatas[index];
		
		index++;//point to 'suffix'
		byte suffix = pakectDatas[index];
		if(suffix != SUFFIX) {
			return false;
		}
		
		byte calChksum = ProtCommon.calChksum(pakectDatas, start+1, end-1);
		if(calChksum != chksum) {
			return false;
		}
		
		if(protDataStruct!=null) {
			protDataStruct.bpPrefix = prefix;
			protDataStruct.bpLength = pktLen;
			protDataStruct.bpControl = ctrl;
			protDataStruct.bpChksum = chksum;
			protDataStruct.bpSuffix = suffix;
			protDataStruct.bpObjects = objList;
		}
		
		return true;
	}
	
	public static BleProtDataStruct parse(byte[] pakectDatas) {
		
		int[] pktStartIdx = new int[1];
		int[] pktEndIdx = new int[1];
		if(!findPktFlag(pakectDatas, 0, pktStartIdx, pktEndIdx)) {
			return null;
		}
		BleProtDataStruct bleProtDataStruct = new BleProtDataStruct();
		if(!checkPktValid(pakectDatas,pktStartIdx[0],pktEndIdx[0],bleProtDataStruct)){
			return null;
		}
		
		return bleProtDataStruct;
	}
}
