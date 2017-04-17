/*
 * Properties.java  2012-2-9 
 * 
 * Copyright (c) 2012, Sohu.com All Rights Reserved. 
 *
 * (Description about the file)
 */
package com.aaread.config;

import java.util.Properties;

import org.springframework.stereotype.Component;


@Component
public class Settings {
	
	private Properties systemSettings;

	/**
	 * @return  the systemSettings
	 */
	public Properties getSystemSettings() {
		return systemSettings;
	}

	/**
	 * @param   systemSettings    the systemSettings to set
	 */
	public void setSystemSettings( Properties systemSettings ) {
		this.systemSettings = systemSettings;
	}
	
	public String getValue(String key) {
		return systemSettings.getProperty( key );
	}
	
}
