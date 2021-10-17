package com.lotus.jewel.ci.main;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class CommandInterfaceMain {

	public static void main(String[] args) {
		Configuration configuration;
		try {
			configuration = new PropertiesConfiguration("config/message.properties");
			String info = configuration.getString("info");
			System.out.println(info);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
}
