package com.hao.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import beacon.MainTest;


public class MyLogger extends Logger{

	protected MyLogger(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	private static Logger logger = Logger.getLogger(MainTest.class);
	private static String configFile = null;
	
	
	public static String getConfigFile() {
		return configFile;
	}

	public static void setConfigFile(String configFile) {
		MyLogger.configFile = configFile;
	}

	public void init() {
		PropertyConfigurator.configure(configFile);
	}
	
	public void myLoggerTest() {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		logger.fatal("fatal");
	}
}
