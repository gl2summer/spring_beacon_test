package com.hao.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class StoragePlatesImpl implements StorageIf {

	private DataSource dataSource;
	
	public StoragePlatesImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public StoragePlatesImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public void put(DataStruct1 ds1) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = dataSource.getConnection();
			String sqlString = SqlText.MakeInsertOneString(ds1.getMyMac(), ds1.getParentMac(), ds1.getMajor(),
					ds1.getMinor(), ds1.getRssiAvr(), ds1.getRssiList());
			Statement smt = conn.createStatement();
			smt.execute(sqlString);
			smt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<DataStruct1> get() {
		// TODO Auto-generated method stub
		List<DataStruct1> list = new ArrayList<DataStruct1>();
		try {
			Connection conn = dataSource.getConnection();
			String sqlString = SqlText.MakeSelectAllString();
			Statement smt = conn.createStatement();
			
			ResultSet rs = smt.executeQuery(sqlString);
			smt.close();
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String myMac = rs.getString("myMac");
				String parentMac = rs.getString("parentMac");
				Integer major = rs.getInt("major");
				Integer minor = rs.getInt("minor");
				Integer rssiAvr = rs.getInt("rssiAvr");
				String rssiList = rs.getString("rssiList");
				
				DataStruct1 ds1 = new DataStruct1(id,myMac,parentMac,major,minor,rssiAvr,rssiList);
				list.add(ds1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public void remove(Integer id) {
		// TODO Auto-generated method stub

		try {
			Connection conn = dataSource.getConnection();
			String sqlString = SqlText.MakeRemoveOneString(id);
			Statement smt = conn.createStatement();
			smt.execute(sqlString);
			smt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
