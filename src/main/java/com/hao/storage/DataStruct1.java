package com.hao.storage;

public class DataStruct1 {

	private Integer id;
	private String myMac;
	private String parentMac;
	private Integer major;
	private Integer minor;
	private Integer rssiAvr;
	private String rssiList;
	
	public DataStruct1(Integer id, String myMac, String parentMac, Integer major, Integer minor, Integer rssiAvr,
			String rssiList) {
		super();
		this.id = id;
		this.myMac = myMac;
		this.parentMac = parentMac;
		this.major = major;
		this.minor = minor;
		this.rssiAvr = rssiAvr;
		this.rssiList = rssiList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMyMac() {
		return myMac;
	}

	public void setMyMac(String myMac) {
		this.myMac = myMac;
	}

	public String getParentMac() {
		return parentMac;
	}

	public void setParentMac(String parentMac) {
		this.parentMac = parentMac;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getMinor() {
		return minor;
	}

	public void setMinor(Integer minor) {
		this.minor = minor;
	}

	public Integer getRssiAvr() {
		return rssiAvr;
	}

	public void setRssiAvr(Integer rssiAvr) {
		this.rssiAvr = rssiAvr;
	}

	public String getRssiList() {
		return rssiList;
	}

	public void setRssiList(String rssiList) {
		this.rssiList = rssiList;
	}
	
}
