package com.hao.storage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlText {

	public static String MakeInsertOneString(String myMac, String parentMac, Integer major, Integer minor, Integer rssiAvr, String rssiList) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String datetime = df.format(new Date());
		//System.out.println(datetime);
		
		String sqlString = String.format("insert into eplates(myMac, parentMac, major, minor, rssiAvr, rssiList, createTime) values('%s', '%s', %d, %d, %d, '%s','%s');",
				myMac, parentMac, major, minor, rssiAvr, rssiList, datetime);
		return sqlString;
	}

	public static String MakeRemoveOneString(int id) {
		String sqlString = String.format("delete from eplates where id=%d;", id);
		return sqlString;
	}
	
	public static String MakeSelectAllString() {
		String sqlString = String.format("select id, myMac, parentMac, major, minor, rssiAvr, rssiList from eplates;");
		return sqlString;
	}
}
