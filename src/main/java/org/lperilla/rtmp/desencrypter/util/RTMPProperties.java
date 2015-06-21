package org.lperilla.rtmp.desencrypter.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RTMPProperties {

	private static Logger logger = LogManager.getLogger(RTMPProperties.class);

	private static RTMPProperties instance;

	private Properties properties;

	private final String FILE_CONFIG = "config.properties";

	private RTMPProperties() {
		this.properties = new Properties();
		File conf = new File(FILE_CONFIG);
		if (!conf.exists()) {
			try {
				conf.createNewFile();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		try {
			this.getProperties().load(new FileInputStream(conf));
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public static RTMPProperties getInstance() {
		if (instance == null) {
			instance = new RTMPProperties();
		}
		return instance;
	}

	public String getProperty(String key){
		return this.getProperties().getProperty(key);
	}
	
	public String getProperty(String key, String defaultValue){
		return this.getProperties().getProperty(key, defaultValue);
	} 
	
}
