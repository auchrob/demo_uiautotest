package com.demo.search.utils;

import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Logger logger = Logger.getLogger(ConfigReader.class);
	private static ConfigReader cr;

	private String sourceCodeDir = "src";
	private String sourceCodeEncoding = "UTF-8";
	private static final String CONFIGFILE = "./res/properties/config.properties";
	private Properties properties;

	private ConfigReader() {
		readConfig(CONFIGFILE);
	}

	public static ConfigReader getInstance() {
		if (cr == null) {
			cr = new ConfigReader();
		}
		return cr;
	}

	private void readConfig(String fileName) {
		properties = getConfig(fileName);
	}

	public String getStr(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 
	 * @param propertyFileName
	 * 
	 * @return
	 */
	private Properties getConfig(String propertyFileName) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(propertyFileName));
		} catch (FileNotFoundException e) {
			properties = null;
			logger.warn("FileNotFoundException:" + propertyFileName);
		} catch (IOException e) {
			properties = null;
			logger.warn("IOException:" + propertyFileName);
		}
		return properties;
	}
}
