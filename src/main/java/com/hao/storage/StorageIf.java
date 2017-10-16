package com.hao.storage;

import java.util.List;

public interface StorageIf {

	public void put(DataStruct1 ds1);//保存一条记录
	public List<DataStruct1> get();//获取所有记录
	public void remove(Integer id);//删除一条记录，以id为筛选条件
}
