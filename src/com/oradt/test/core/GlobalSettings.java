package com.oradt.test.core;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Global Settings
 */
public class GlobalSettings {

	public static Properties prop = getProperties();
	
	public static String baseURL = prop.getProperty("baseURL");	
	public static String DBAddr = prop.getProperty("DBAddr");  
	public static String DBTable = prop.getProperty("DBTable");  
	public static String DBDriver = prop.getProperty("DBDriver");
	public static String DBuser = prop.getProperty("DBuser"); 
	public static String DBpassword = prop.getProperty("DBpassword");

	public static String getProperty(String property) {
		return prop.getProperty(property);
	}
	
	public static Properties getProperties() {
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("resource/global.properties");
			prop.load(file);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}