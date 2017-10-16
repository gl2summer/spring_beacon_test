package com.hao.storage;

public class SqlText {

	public static String MakeInsertOneString(String myMac, String parentMac, Integer major, Integer minor, Integer rssiAvr, String rssiList) {
		String sqlString = String.format("insert into eplates(myMac, parentMac, major, minor, rssiAvr, rssiList) values('%s', '%s', %d, %d, %d, '%s');",
				myMac, parentMac, major, minor, rssiAvr, rssiList);
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
